package com.gohelpfund.api.v1.authentication.model.fundraiser.status;

import java.io.Serializable;

public enum FundraiserStatusSubType implements Serializable {
    VERIFICATION_REQUIRED,
    ACCOUNT_DELETED,
    ACCOUNT_TERMINATED,
    ACCOUNT_LIVE
}
