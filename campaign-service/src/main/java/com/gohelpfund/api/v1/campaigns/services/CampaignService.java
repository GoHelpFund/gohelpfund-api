package com.gohelpfund.api.v1.campaigns.services;

import com.gohelpfund.api.v1.campaigns.clients.FundraiserFeignClient;
import com.gohelpfund.api.v1.campaigns.model.Campaign;
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
    FundraiserFeignClient fundraiserFeignClient;

    @Autowired
    private CampaignMediaResourceService resources;

    @Autowired
    private CampaignStatusService status;

    private Resource<Fundraiser> retrieveFundraiser(String fundraiserId, String clientType) {
        Resource<Fundraiser> fundraiser;

        switch (clientType) {
            case "feign":
                fundraiser = fundraiserFeignClient.getFundraiser(fundraiserId);
                break;
            default:
                fundraiser = fundraiserFeignClient.getFundraiser(fundraiserId);
        }
        return fundraiser;
    }


    public List<Campaign> getCampaigns() {
        List<Campaign> campaigns = repository.findAll();
        campaigns.forEach(campaign -> campaign.withMediaResources(resources.getAll(campaign.getCampaignId())));

        return campaigns;
    }

    public Optional<Campaign> getCampaignById(String campaignId) {
        Optional<Campaign> campaign = repository.findByCampaignId(campaignId);
        Fundraiser fundraiser = retrieveFundraiser(campaign.get().getFundraiserId(), "feign").getContent();
        campaign.get()
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
