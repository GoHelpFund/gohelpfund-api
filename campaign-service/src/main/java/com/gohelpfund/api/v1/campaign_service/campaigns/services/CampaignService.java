package com.gohelpfund.api.v1.campaign_service.campaigns.services;

import com.gohelpfund.api.v1.campaign_service.campaigns.clients.CategoryRestTemplateClient;
import com.gohelpfund.api.v1.campaign_service.campaigns.clients.FundraiserRestTemplateClient;
import com.gohelpfund.api.v1.campaign_service.campaigns.clients.WalletRestTemplateClient;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.Campaign;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.category.Category;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.Wallet;
import com.gohelpfund.api.v1.campaign_service.campaigns.repository.CampaignRepository;
import com.gohelpfund.api.v1.campaign_service.utils.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    WalletRestTemplateClient walletClient;

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

    private Wallet getWallet(String id) {
        Wallet wallet = walletClient.getWallet(id);

        if (wallet != null) {
            logger.debug("GET | /api/v1/wallets/{id} | found | wallet id: {}", id);
        } else {
            logger.debug("GET | /api/v1/wallets/{id} | not found | wallet id: {}", id);
        }
        return wallet;
    }

    private Wallet createWallet(String campaignId, HttpEntity httpEntity) {
        Wallet newWallet = walletClient.createWallet(httpEntity);

        if (newWallet != null) {
            logger.debug("POST | /api/v1/wallets | created | campaign id: {} wallet id: {}", campaignId, newWallet.getId());
        } else {
            logger.debug("POST | /api/v1/wallets | creation failed | campaign id: {}", campaignId);
        }

        return newWallet;
    }

    private Wallet updateWallet(String campaignId, String walletId, HttpEntity httpEntity) {
        Wallet newWallet = walletClient.updateWallet(walletId, httpEntity);

        if (newWallet != null) {
            logger.debug("POST | /api/v1/wallets/{}/donate | updated | campaign id: {} wallet id: {}", walletId, campaignId, newWallet.getId());
        } else {
            logger.debug("POST | /api/v1/wallets/{}/donate | update failed | campaign id: {}", walletId, campaignId);
        }

        return newWallet;
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
                        campaign.withWallet(getWallet(campaign.getWalletId()));
                    }
            );
        }
        return campaigns;
    }

    public Campaign getCampaignById(String campaignId) {
        Campaign campaign = repository.findByCampaignId(campaignId);

        if (campaign == null) {
            logger.debug("GET | PostgreSQL | not found | campaign id: {}", campaignId);
        } else {
            logger.debug("GET | PostgreSQL | found | campaign id: {}", campaignId);
            Fundraiser fundraiser = getFundraiser(campaign.getFundraiserId())
                    .withId(campaign.getFundraiserId());
            Category category = getCategory(campaign.getCategoryId())
                    .withId(campaign.getCategoryId());
            Wallet wallet = getWallet(campaign.getWalletId())
                    .withId(campaign.getWalletId());

            campaign
                    .withCategory(category)
                    .withMediaResources(resources.getAll(campaign.getCampaignId()))
                    .withFundraiser(fundraiser)
                    .withWallet(wallet);
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
        Wallet wallet = createWallet(id, getHttpEntity(id));

        campaign
                .withId(id)
                .withStatus(status.save(id))
                .withMediaResources(resources.saveAll(campaign.getResources(), id))
                .withCategoryId(categoryId)
                .withFundraiserId(fundraiser.getId())
                .withWalletId(wallet.getId());

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


    public Wallet updateCampaign(String campaignId, String walletId, String fundraiserId, Integer amount) {

        String fundraiserName = getFundraiser(fundraiserId).getName();

        Wallet wallet = updateWallet(campaignId, walletId, getHttpEntity(fundraiserId, fundraiserName, amount));

        logger.debug("PUT | PostgreSQL | updated | wallet id: {} ", wallet.getId());

        return wallet;
    }

    private void deleteCampaign(Campaign campaign) {
        repository.delete(campaign);
        logger.debug("DELETE | PostgreSQL | removed | campaign id: {} ", campaign.getCampaignId());
    }

    private HttpEntity<Map<String, String>> getHttpEntity(String campaignId) {
        HttpHeaders headers = new HttpHeaders();
        String token = UserContextHolder.getContext().getAuthToken();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");

        Map<String, String> map = new HashMap<>();
        map.put("entity_id", campaignId);
        map.put("type", "campaign");

        return new HttpEntity<>(map, headers);
    }

    private HttpEntity<Map<String, Object>> getHttpEntity(String fundraiserId, String fundraiserName, Integer amount) {
        HttpHeaders headers = new HttpHeaders();
        String token = UserContextHolder.getContext().getAuthToken();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");

        Map<String, Object> map = new HashMap<>();
        map.put("entity_id", fundraiserId);
        map.put("entity_name", fundraiserName);
        map.put("amount", amount);
        map.put("type", "fundraiser");

        return new HttpEntity<>(map, headers);
    }
}
