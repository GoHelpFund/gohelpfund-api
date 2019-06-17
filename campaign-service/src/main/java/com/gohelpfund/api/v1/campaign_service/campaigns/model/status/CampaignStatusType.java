package com.gohelpfund.api.v1.campaign_service.campaigns.model.status;

public enum CampaignStatusType {
    PENDING,    // campaign is unlisted
    CREATED,    // campaign is now public and donors can donate to it
    COMPLETED,  // campaign is completed successfully
    CANCELED    // campaign is canceled
}