package com.gohelpfund.api.v1.campaign_service.campaigns.services;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.expense.CampaignExpense;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.mediaresource.CampaignMediaResource;
import com.gohelpfund.api.v1.campaign_service.campaigns.repository.CampaignExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CampaignExpenseService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignExpenseService.class);

    @Autowired
    private CampaignExpenseRepository repository;

    public List<CampaignExpense> getAll(String campaignId) {
        List<CampaignExpense> expenses = repository.findByCampaignId(campaignId);

        if (expenses != null) {
            logger.debug("GET | PostgreSQL | found | campaign_expenses  size: {}", expenses.size());
        } else {
            logger.debug("GET | PostgreSQL | empty | campaign_expenses size: 0");
        }

        return expenses;
    }

    public List<CampaignExpense> saveAll(List<CampaignExpense> expenses, String campaignId) {
        List<CampaignExpense> newExpenses = new ArrayList<>();

        if (expenses != null && !expenses.isEmpty()) {
            for (CampaignExpense expense : expenses) {
                newExpenses.add(saveOne(expense, campaignId));
            }
            logger.debug("POST | PostgreSQL | saved | campaign_expenses size: {} ", expenses.size());
        }
        return newExpenses;
    }

    public CampaignExpense saveOne(CampaignExpense expense, String campaignId) {
        expense
                .withId(UUID.randomUUID().toString())
                .withCampaignId(campaignId);

        CampaignExpense newExpense = repository.save(expense);
        logger.debug("POST | PostgreSQL | created | campaign_expense id: {} ", newExpense.getExpenseId());

        return newExpense;
    }
}
