package com.gohelpfund.api.v1.campaigns.clients;

import com.gohelpfund.api.v1.campaigns.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.campaigns.repository.FundraiserRedisRepository;
import com.gohelpfund.api.v1.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FundraiserRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FundraiserRedisRepository orgRedisRepo;

    private static final Logger logger = LoggerFactory.getLogger(FundraiserRestTemplateClient.class);

    private Fundraiser checkRedisCache(String fundraiserId) {
        try {
            return orgRedisRepo.findFundraiser(fundraiserId);
        }
        catch (Exception ex){
            logger.error("Error encountered while trying to retrieve fundraiser {} check Redis Cache.  Exception {}", fundraiserId, ex);
            return null;
        }
    }

    private void cacheFundraiserObject(Fundraiser fundraiser) {
        try {
            orgRedisRepo.saveFundraiser(fundraiser);
        }catch (Exception ex){
            logger.error("Unable to cache fundraiser {} in Redis. Exception {}", fundraiser.getId(), ex);
        }
    }

    public Fundraiser getFundraiser(String fundraiserId){
        logger.debug("In Campaign Service.getFundraiser: {}", UserContext.getCorrelationId());

        Fundraiser fundraiser = checkRedisCache(fundraiserId);

        if (fundraiser!=null){
            logger.debug("I have successfully retrieved an fundraiser {} from the redis cache: {}", fundraiserId, fundraiser);
            return fundraiser;
        }

        logger.debug("Unable to locate fundraiser from the redis cache: {}.", fundraiserId);

        ResponseEntity<Fundraiser> restExchange =
                restTemplate.exchange(
                        "http://fundraiser-service:9200/api/v1/fundraisers/{fundraiserId}",
                        HttpMethod.GET,
                        null, Fundraiser.class, fundraiserId);
        /*Save the record from cache*/
        fundraiser = restExchange.getBody();

        if (fundraiser!=null) {
            cacheFundraiserObject(fundraiser);
        }

        return fundraiser;
    }

    public Fundraiser generateFundraiser(){
        logger.debug("In Campaign Service.generateFundraiser: {}", UserContext.getCorrelationId());

        HttpEntity<Fundraiser> request = new HttpEntity<>(new Fundraiser());
        ResponseEntity<Fundraiser> restExchange =
                restTemplate.exchange(
                        "http://fundraiser-service:9200/api/v1/fundraisers/",
                        HttpMethod.POST,
                        request, Fundraiser.class);
        /*Save the record from cache*/
        Fundraiser fundraiser = restExchange.getBody();

        if (fundraiser!=null) {
            cacheFundraiserObject(fundraiser);
        }

        return fundraiser;
    }


}
