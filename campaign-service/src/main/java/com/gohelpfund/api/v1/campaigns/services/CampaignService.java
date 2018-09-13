package com.gohelpfund.api.v1.campaigns.services;

import com.gohelpfund.api.v1.campaigns.clients.CategoryFeignClient;
import com.gohelpfund.api.v1.campaigns.clients.FundraiserFeignClient;
import com.gohelpfund.api.v1.campaigns.model.Campaign;
import com.gohelpfund.api.v1.campaigns.model.category.Category;
import com.gohelpfund.api.v1.campaigns.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.campaigns.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository repository;

    @Autowired
    FundraiserFeignClient fundraiserClient;

    @Autowired
    CategoryFeignClient categoryClient;

    @Autowired
    private CampaignMediaResourceService resources;

    @Autowired
    private CampaignStatusService status;

    private Fundraiser getFundraiser(String id, String clientType) {
        Resource<Fundraiser> fundraiser;

        switch (clientType) {
            case "feign":
                fundraiser = fundraiserClient.getFundraiser(id);
                break;
            default:
                fundraiser = fundraiserClient.getFundraiser(id);
        }
        return fundraiser.getContent();
    }
    private Category getCategory(String id, String clientType) {
        Resource<Category> category;

        switch (clientType) {
            case "feign":
                category = categoryClient.getCategory(id);
                break;
            default:
                category = categoryClient.getCategory(id);
        }
        return category.getContent();
    }

    public List<Campaign> getCampaigns() {
        List<Campaign> campaigns = repository.findAll();
        campaigns.forEach(campaign -> campaign.withMediaResources(resources.getAll(campaign.getCampaignId())));

        return campaigns;
    }

    public Optional<Campaign> getCampaignById(String campaignId) {
        Optional<Campaign> campaign = repository.findByCampaignId(campaignId);
        Fundraiser fundraiser = getFundraiser(campaign.get().getFundraiserId(), "feign").withId(campaign.get().getFundraiserId());
        Category category = getCategory(campaign.get().getCategoryId(), "feign").withId(campaign.get().getCategoryId());
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
        campaign.withId(id)
                .withStatus(status.save(id))
                .withMediaResources(resources.saveAll(campaign.getResources(), id))
                .withFundraiser(new Fundraiser());

        return repository.save(campaign);
    }

    public Campaign updateCampaign(Campaign campaign) {
        return repository.save(campaign);
    }

    public void deleteCampaign(Campaign campaign) {
        repository.delete(campaign);
    }

}
