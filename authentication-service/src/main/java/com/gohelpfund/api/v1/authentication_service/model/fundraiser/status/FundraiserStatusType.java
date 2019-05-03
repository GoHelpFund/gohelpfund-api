package com.gohelpfund.api.v1.authentication_service.model.fundraiser.status;

import java.io.Serializable;

public enum FundraiserStatusType implements Serializable {
    PENDING,
    COMPLETED,
    DISABLED;
}