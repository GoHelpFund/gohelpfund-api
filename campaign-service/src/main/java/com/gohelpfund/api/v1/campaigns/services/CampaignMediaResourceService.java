package com.gohelpfund.api.v1.campaigns.services;

import com.gohelpfund.api.v1.campaigns.model.mediaresource.CampaignMediaResource;
import com.gohelpfund.api.v1.campaigns.repository.CampaignMediaResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CampaignMediaResourceService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignMediaResourceService.class);
    @Autowired
    private CampaignMediaResourceRepository repository;

    public List<CampaignMediaResource> getAll(String campaignId) {
        List<CampaignMediaResource> mediaResources = repository.findByCampaignId(campaignId);

        if (mediaResources != null) {
            logger.debug("GET | PostgreSQL | found | campaign_media_resources  size: {}", mediaResources.size());
        } else {
            logger.debug("GET | PostgreSQL | empty | campaign_media_resources size: 0");
        }

        return mediaResources;
    }

    public List<CampaignMediaResource> saveAll(List<CampaignMediaResource> resources, String campaignId) {
        List<CampaignMediaResource> newResources = new ArrayList<>();

        if (resources != null && !resources.isEmpty()) {
            for (CampaignMediaResource resource : resources) {
                newResources.add(saveOne(resource, campaignId));
            }
            logger.debug("POST | PostgreSQL | saved | campaign_media_resources size: {} ", resources.size());
        }
        return newResources;
    }

    public CampaignMediaResource saveOne(CampaignMediaResource resource, String campaignId) {
        resource
                .withId(UUID.randomUUID().toString())
                .withCampaignId(campaignId);

        CampaignMediaResource newResource = repository.save(resource);
        logger.debug("POST | PostgreSQL | created | campaign_media_resource id: {} ", newResource.getResourceId());

        return newResource;
    }

    public CampaignMediaResource update(CampaignMediaResource campaignMediaResource) {
        CampaignMediaResource newMediaResource = repository.save(campaignMediaResource);
        logger.debug("PUT | PostgreSQL | updated | campaign_status id: {} ", newMediaResource.getResourceId());

        return newMediaResource;
    }

    private void delete(CampaignMediaResource campaignMediaResource) {
        repository.delete(campaignMediaResource.getResourceId());
        logger.debug("DELETE | PostgreSQL | removed | campaign_media_resource id: {} ", campaignMediaResource.getResourceId());
    }

}
