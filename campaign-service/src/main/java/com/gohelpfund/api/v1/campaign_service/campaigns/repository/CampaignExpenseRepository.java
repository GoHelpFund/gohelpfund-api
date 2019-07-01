package com.gohelpfund.api.v1.campaign_service.campaigns.repository;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.expense.CampaignExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignExpenseRepository extends JpaRepository<CampaignExpense,String> {
    List<CampaignExpense> findByCampaignId(String campaignId);
}