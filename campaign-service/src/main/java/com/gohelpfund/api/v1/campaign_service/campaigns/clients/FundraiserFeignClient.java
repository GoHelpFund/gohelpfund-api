package com.gohelpfund.api.v1.campaign_service.campaigns.clients;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.fundraiser.Fundraiser;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public interface FundraiserFeignClient {
    @RequestMapping(
            value = "/v1/fundraisers/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Resource<Fundraiser> getFundraiser(@PathVariable("id") String id);

    @RequestMapping(
            value = "/v1/fundraisers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Resource<Fundraiser> createFundraiser();
}
