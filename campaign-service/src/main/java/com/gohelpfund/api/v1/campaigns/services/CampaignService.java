package com.gohelpfund.api.v1.campaigns.services;

import com.gohelpfund.api.v1.campaigns.clients.CategoryRestTemplateClient;
import com.gohelpfund.api.v1.campaigns.clients.FundraiserRestTemplateClient;
import com.gohelpfund.api.v1.campaigns.model.Campaign;
import com.gohelpfund.api.v1.campaigns.model.category.Category;
import com.gohelpfund.api.v1.campaigns.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.campaigns.repository.CampaignRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CampaignService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignService.class);

    @Autowired
    private CampaignRepository repository;

    @Autowired
    FundraiserRestTemplateClient fundraiserClient;

    @Autowired
    CategoryRestTemplateClient categoryClient;

    @Autowired
    private CampaignMediaResourceService resources;

    @Autowired
    private CampaignStatusService status;

    @HystrixCommand(fallbackMethod = "buildFallbackFundraiser",
            threadPoolKey = "fundraiserByIdThreadPool",
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize", value = "30"),
                            @HystrixProperty(name = "maxQueueSize", value = "10")},
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")}
    )
    private Fundraiser getFundraiser(String id) {
        return fundraiserClient.getFundraiser(id);
    }

    @HystrixCommand(fallbackMethod = "buildFallbackCategory",
            threadPoolKey = "categoryByIdThreadPool",
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize", value = "30"),
                            @HystrixProperty(name = "maxQueueSize", value = "10")},
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")}
    )
    private Category getCategory(String id) {
        Category category = categoryClient.getCategory(id);

        if (category != null) {
            logger.debug("GET | /api/v1/categories/{id} | found | category id: {}", id);
        } else {
            logger.debug("GET | /api/v1/categories/{id} | not found | category id: {}", id);
        }
        return category;
    }

    private Category buildFallbackCategory(String id) {
        return new Category();
    }

    public List<Campaign> getCampaigns() {
        List<Campaign> campaigns = repository.findAll();
        if (campaigns == null) {
            logger.debug("GET | PostgreSQL | empty | campaigns size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | campaigns size: {}", campaigns.size());
            campaigns.forEach(campaign -> {
                        campaign.withMediaResources(resources.getAll(campaign.getCampaignId()));
                        campaign.withFundraiser(getFundraiser(campaign.getFundraiserId()));
                        campaign.withCategory(getCategory(campaign.getCategoryId()));
                    }
            );
        }
        return campaigns;
    }

    public Campaign getCampaignById(String campaignId) {
        Campaign campaign = repository.findByCampaignId(campaignId);

        if (campaign == null){
            logger.debug("GET | PostgreSQL | not found | campaign id: {}", campaignId);
        } else {
            logger.debug("GET | PostgreSQL | found | campaign id: {}", campaignId);
            Fundraiser fundraiser = getFundraiser(campaign.getFundraiserId())
                    .withId(campaign.getFundraiserId());
            Category category = getCategory(campaign.getCategoryId())
                    .withId(campaign.getCategoryId());
            campaign
                    .withCategory(category)
                    .withMediaResources(resources.getAll(campaign.getCampaignId()))
                    .withFundraiser(fundraiser);
        }
        return campaign;
    }


    public Campaign save(Campaign campaign) {
        String id = UUID.randomUUID().toString();

        Fundraiser fundraiser = getFundraiser(campaign.getFundraiserId())
                .withId(campaign.getFundraiserId());

        String categoryId = campaign.getCategory().getId();
        Category category = getCategory(categoryId)
                .withId(categoryId);

        campaign
                .withId(id)
                .withStatus(status.save(id))
                .withMediaResources(resources.saveAll(campaign.getResources(), id))
                .withCategoryId(categoryId)
                .withFundraiserId(fundraiser.getId());

        Campaign newCampaign = repository.save(campaign);
        logger.debug("POST | PostgreSQL | created | campaign id: {} ", newCampaign.getCampaignId());

        return campaign
                .withCategory(category)
                .withFundraiser(fundraiser);
    }

    public Campaign updateCampaign(Campaign campaign) {
        Campaign newCampaign = repository.save(campaign);
        logger.debug("PUT | PostgreSQL | updated | campaign id: {} ", newCampaign.getCampaignId());

        return newCampaign;
    }

    private void deleteCampaign(Campaign campaign) {
        repository.delete(campaign);
        logger.debug("DELETE | PostgreSQL | removed | campaign id: {} ", campaign.getCampaignId());
    }

}
