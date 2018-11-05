package com.gohelpfund.api.v1.campaigns.model.fundraiser.status;

import java.io.Serializable;

public enum FundraiserStatusSubType implements Serializable {
    REGISTRATION_REQUIRED,
    VERIFICATION_REQUIRED,
    ACCOUNT_DELETED,
    ACCOUNT_TERMINATED,
    ACCOUNT_LIVE
}
