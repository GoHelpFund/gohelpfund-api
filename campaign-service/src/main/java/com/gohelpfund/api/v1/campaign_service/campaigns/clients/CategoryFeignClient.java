package com.gohelpfund.api.v1.campaign_service.campaigns.clients;


import com.gohelpfund.api.v1.campaign_service.campaigns.model.category.Category;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public interface CategoryFeignClient {
    @RequestMapping(
            value = "/v1/categories/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    Resource<Category> getCategory(@PathVariable("id") String id);
}
