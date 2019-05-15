package com.gohelpfund.api.v1.donation_service.wallets.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SenderDetails {

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("sender_address")
    private String senderAddress;
}
