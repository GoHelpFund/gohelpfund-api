package com.gohelpfund.api.v1.fundraisers.controllers.exceptions;

public class FundraiserNotFoundException extends RuntimeException {

    public FundraiserNotFoundException(String id) {
        super("Could not find fundraiser " + id);
    }
}