package com.gohelpfund.api.v1.campaigns.model;

public enum CampaignStatusSubType {
    REGISTRATION_REQUIRED,
    VERIFICATION_REQUIRED,
    APPROVAL_IN_PROGRESS,
    APPROVED,
    FUNDS_RETURNED,
    NO_FUNDS_RAISED,
    SOFT_CAP_REACHED,
    HARD_CAP_REACHED;
}
