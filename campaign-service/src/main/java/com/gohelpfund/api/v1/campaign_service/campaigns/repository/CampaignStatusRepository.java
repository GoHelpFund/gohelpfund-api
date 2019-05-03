package com.gohelpfund.api.v1.campaign_service.campaigns.repository;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.status.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignStatusRepository extends JpaRepository<CampaignStatus, String> {
    CampaignStatus findByCampaignId(String campaignId);
    CampaignStatus findByStatusId(String statusId);
}
