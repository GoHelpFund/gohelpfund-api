package com.gohelpfund.api.v1.campaigns.controllers.exceptions;

public class CampaignNotFoundException extends RuntimeException {

    public CampaignNotFoundException(String id) {
        super("Could not find campaign " + id);
    }
}