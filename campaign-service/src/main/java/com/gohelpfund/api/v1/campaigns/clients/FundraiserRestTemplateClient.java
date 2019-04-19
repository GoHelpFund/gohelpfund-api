package com.gohelpfund.api.v1.campaigns.clients;

import com.gohelpfund.api.v1.campaigns.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.campaigns.repository.FundraiserRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FundraiserRestTemplateClient {
    private static final Logger logger = LoggerFactory.getLogger(FundraiserRestTemplateClient.class);
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Tracer tracer;

    @Autowired
    FundraiserRedisRepository fundraiserRedisRepo;

    private Fundraiser checkRedisCache(String fundraiserId) {
        Span newSpan = tracer.createSpan("readFundraiserDataFromRedis");
        try {
            return fundraiserRedisRepo.findFundraiser(fundraiserId);
        }
        catch (Exception ex){
            logger.error("GET | Redis | error | fundraiser id: {} | exception: {}", fundraiserId, ex);
            return null;
        } finally {
            newSpan.tag("peer.service", "redis");
            newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
            tracer.close(newSpan);
        }
    }

    private void cacheFundraiserObject(Fundraiser fundraiser) {
        try {
            fundraiserRedisRepo.saveFundraiser(fundraiser);
            logger.debug("POST | Redis | saved | fundraiser id: {} ", fundraiser.getId());
        }catch (Exception ex){
            logger.error("POST | Redis | error | fundraiser id: {} | exception: {}", fundraiser.getId(), ex);
        }
    }

    public Fundraiser getFundraiser(String fundraiserId){

        Fundraiser fundraiser = checkRedisCache(fundraiserId);

        if (fundraiser!=null){
            logger.debug("GET | Redis | found | fundraiser id: {}", fundraiserId);
            return fundraiser;
        }
        logger.debug("GET | Redis | not found | fundraiser id: {}", fundraiserId);

        ResponseEntity<Fundraiser> restExchange =
                restTemplate.exchange(
                        "http://fundraiser-service:9200/api/v1/fundraisers/{fundraiserId}",
                        HttpMethod.GET,
                        null, Fundraiser.class, fundraiserId);
        /*Save the record from cache*/
        fundraiser = restExchange.getBody();


        if (fundraiser!=null) {
            logger.debug("GET | /api/v1/fundraisers/{id} | found | fundraiser id: {}", fundraiser.getId());
            cacheFundraiserObject(fundraiser);
        }

        return fundraiser;
    }

}
