package com.gohelpfund.api.v1.campaigns.repository;

import com.gohelpfund.api.v1.campaigns.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String> {
    Campaign findByCampaignId(String campaignId);

}
