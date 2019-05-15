package com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@JsonPropertyOrder({"fundraiser_id"})
public class HelpWalletBacker implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("fundraiser_id")
    private String fundraiser_id;

    public HelpWalletBacker(){

    }

    public String getFundraiser_id() {
        return fundraiser_id;
    }

    public void setFundraiser_id(String fundraiser_id) {
        this.fundraiser_id = fundraiser_id;
    }
}
