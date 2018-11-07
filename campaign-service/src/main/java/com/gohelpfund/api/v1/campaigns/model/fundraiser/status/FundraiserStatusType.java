package com.gohelpfund.api.v1.campaigns.model.fundraiser.status;

import java.io.Serializable;

public enum FundraiserStatusType implements Serializable {
    PENDING,
    COMPLETED,
    DISABLED;
}