package com.gohelpfund.api.v1.campaigns.clients;


import com.gohelpfund.api.v1.campaigns.model.fundraiser.Fundraiser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("fundraiserservice")
public interface FundraiserFeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/v1/fundraisers/{fundraiserId}",
            consumes="application/json")
    Resource<Fundraiser> getFundraiser(@PathVariable("fundraiserId") String fundraiserId);

    @RequestMapping(
            method= RequestMethod.POST,
            value="/v1/fundraisers",
            consumes="application/json")
    Resource<Fundraiser> createFundraiser();
}
