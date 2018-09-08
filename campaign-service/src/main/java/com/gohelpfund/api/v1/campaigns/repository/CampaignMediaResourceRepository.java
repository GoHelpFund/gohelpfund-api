package com.gohelpfund.api.v1.campaigns.repository;

import com.gohelpfund.api.v1.campaigns.model.CampaignMediaResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignMediaResourceRepository extends JpaRepository<CampaignMediaResource,String> {
    public Optional<CampaignMediaResource> findByResourceId(String resourceId);
    public List<CampaignMediaResource> findByCampaignId(String campaignId);
}
