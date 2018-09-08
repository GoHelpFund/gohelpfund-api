package com.gohelpfund.api.v1.campaigns.services;

import com.gohelpfund.api.v1.campaigns.model.CampaignMediaResource;
import com.gohelpfund.api.v1.campaigns.repository.CampaignMediaResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CampaignMediaResourceService {

    @Autowired
    private CampaignMediaResourceRepository repository;

    public List<CampaignMediaResource> getAll(String campaignId) {
        return repository.findByCampaignId(campaignId);
    }

    public List<CampaignMediaResource> saveAll(List<CampaignMediaResource> resources, String campaignId) {
        List<CampaignMediaResource> newResources = new ArrayList<>();

        if(resources != null && !resources.isEmpty()) {
            for(CampaignMediaResource resource: resources){
                newResources.add(saveOne(resource, campaignId));
            }
        }
        return newResources;
    }

    public CampaignMediaResource saveOne(CampaignMediaResource resource, String campaignId) {
        final String id = UUID.randomUUID().toString();
        resource.withId(id);
        resource.withCampaignId(campaignId);

        return repository.save(resource);
    }

    public void update(CampaignMediaResource campaignMediaResource) {
        repository.save(campaignMediaResource);
    }

    public void delete(CampaignMediaResource campaignMediaResource) {
        repository.delete(campaignMediaResource.getResourceId());
    }

}
