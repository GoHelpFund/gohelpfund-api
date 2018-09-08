package com.gohelpfund.api.v1.campaigns.services;

import com.gohelpfund.api.v1.campaigns.model.Campaign;
import com.gohelpfund.api.v1.campaigns.repository.CampaignRepository;
import com.gohelpfund.api.v1.fundraisers.services.FundraiserService;
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
    private CampaignMediaResourceService resources;

    @Autowired
    private CampaignStatusService status;

    @Autowired
    private FundraiserService fundraiser;

    public List<Campaign> getCampaigns() {
        List<Campaign> campaigns = repository.findAll();
        campaigns.forEach(campaign -> campaign.withMediaResources(resources.getAll(campaign.getCampaignId())));

        return campaigns;
    }

    public Optional<Campaign> getCampaignById(String campaignId) {
        Optional<Campaign> campaign =  repository.findByCampaignId(campaignId);
        campaign.get().withMediaResources(resources.getAll(campaign.get().getCampaignId()));
        return campaign;
    }


    public Campaign save(Campaign campaign) {
        String id = UUID.randomUUID().toString();
        campaign.withId(id)
                .withStatus(status.save(id))
                .withMediaResources(resources.saveAll(campaign.getResources(), id))
                .withFundraiser(fundraiser.save());

        return repository.save(campaign);
    }

    public Campaign updateCampaign(Campaign campaign) {
        return repository.save(campaign);
    }

    public void deleteCampaign(Campaign campaign) {
        repository.delete(campaign);
    }

}
