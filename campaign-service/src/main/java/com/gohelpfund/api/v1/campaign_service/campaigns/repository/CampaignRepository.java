package com.gohelpfund.api.v1.campaign_service.campaigns.repository;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String> {
    Campaign findByCampaignId(String campaignId);

}
