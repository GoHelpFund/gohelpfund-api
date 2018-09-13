package com.gohelpfund.api.v1.campaigns.clients;


import com.gohelpfund.api.v1.campaigns.model.fundraiser.Fundraiser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("fundraiser-service")
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
