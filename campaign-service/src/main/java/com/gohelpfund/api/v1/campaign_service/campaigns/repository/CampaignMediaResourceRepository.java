package com.gohelpfund.api.v1.campaign_service.campaigns.repository;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.mediaresource.CampaignMediaResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignMediaResourceRepository extends JpaRepository<CampaignMediaResource,String> {
    Optional<CampaignMediaResource> findByResourceId(String resourceId);
    List<CampaignMediaResource> findByCampaignId(String campaignId);
}
