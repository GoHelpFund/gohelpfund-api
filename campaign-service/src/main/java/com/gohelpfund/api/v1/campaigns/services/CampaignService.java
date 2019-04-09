package com.gohelpfund.api.v1.campaigns.services;

import com.gohelpfund.api.v1.campaigns.clients.CategoryRestTemplateClient;
import com.gohelpfund.api.v1.campaigns.clients.FundraiserRestTemplateClient;
import com.gohelpfund.api.v1.campaigns.controllers.exceptions.EntityNotFoundException;
import com.gohelpfund.api.v1.campaigns.model.Campaign;
import com.gohelpfund.api.v1.campaigns.model.category.Category;
import com.gohelpfund.api.v1.campaigns.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.campaigns.repository.CampaignRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CampaignService {

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

    private Fundraiser generateFundraiser() {
        return fundraiserClient.generateFundraiser();
    }

    private Fundraiser buildFallbackFundraiser(String id) {
        return new Fundraiser();
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
        return categoryClient.getCategory(id);
    }

    private Category buildFallbackCategory(String id) {
        return new Category();
    }

    public List<Campaign> getCampaigns() {
        List<Campaign> campaigns = repository.findAll();
        campaigns.forEach(campaign -> {
                    campaign.withMediaResources(resources.getAll(campaign.getCampaignId()));
                    campaign.withFundraiser(getFundraiser(campaign.getFundraiserId()));
                    campaign.withCategory(getCategory(campaign.getCategoryId()));
                }
        );
        return campaigns;
    }

    public Optional<Campaign> getCampaignById(String campaignId) {
        Optional<Campaign> campaign = repository.findByCampaignId(campaignId);

        campaign.orElseThrow(() -> new EntityNotFoundException(Campaign.class, "id", campaignId));

        Fundraiser fundraiser = getFundraiser(campaign.get().getFundraiserId())
                .withId(campaign.get().getFundraiserId());
        Category category = getCategory(campaign.get().getCategoryId())
                .withId(campaign.get().getCategoryId());
        campaign.get()
                .withCategory(category)
                .withMediaResources(resources.getAll(campaign.get().getCampaignId()))
                .withFundraiser(fundraiser);
        return campaign;
    }


    public Campaign save(Campaign campaign) {
        String id = UUID.randomUUID().toString();
        /**
         * TODO
         * add create new fundraiser logic
         */

        Fundraiser fundraiser = generateFundraiser();

        String categoryId = campaign.getCategory().getId();
        Category category = getCategory(categoryId)
                .withId(categoryId);

        campaign.withId(id)
                .withStatus(status.save(id))
                .withMediaResources(resources.saveAll(campaign.getResources(), id))
                .withCategoryId(categoryId)
                .withFundraiserId(fundraiser.getId());

        return repository
                .save(campaign)
                .withCategory(category)
                .withFundraiser(fundraiser);
    }

    public Campaign updateCampaign(Campaign campaign) {
        return repository.save(campaign);
    }

    public void deleteCampaign(Campaign campaign) {
        repository.delete(campaign);
    }

}
