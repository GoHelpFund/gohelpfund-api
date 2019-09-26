package com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.bitcoin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"fundraiser_id"})
public class BitcoinWalletBacker implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("fundraiser_id")
    private String fundraiser_id;

    public BitcoinWalletBacker(){

    }

    public String getFundraiser_id() {
        return fundraiser_id;
    }

    public void setFundraiser_id(String fundraiser_id) {
        this.fundraiser_id = fundraiser_id;
    }
}
