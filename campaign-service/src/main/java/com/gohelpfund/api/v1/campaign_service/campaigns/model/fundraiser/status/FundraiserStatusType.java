package com.gohelpfund.api.v1.campaign_service.campaigns.model.fundraiser.status;

import java.io.Serializable;

public enum FundraiserStatusType implements Serializable {
    PENDING,
    COMPLETED,
    DISABLED;
}