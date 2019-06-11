package com.gohelpfund.api.v1.campaign_service.events.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"id"})
public class EventDonateResponse implements Serializable {
    @JsonProperty("total_amount_donated")
    private Integer totalAmountDonated;

    public EventDonateResponse(){

    }

    public int getTotalAmountDonated() {
        return totalAmountDonated;
    }

    public void setTotalAmountDonated(int totalAmountDonated) {
        this.totalAmountDonated = totalAmountDonated;
    }
}
