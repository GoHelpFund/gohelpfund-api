package com.gohelpfund.api.v1.campaign_service.events.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"total_amount_donated"})
public class EventDonateResponse implements Serializable {
    @JsonProperty("total_amount_donated")
    private Double totalAmountDonated;

    public EventDonateResponse(){

    }

    public Double getTotalAmountDonated() {
        return totalAmountDonated;
    }

    public void setTotalAmountDonated(Double totalAmountDonated) {
        this.totalAmountDonated = totalAmountDonated;
    }
}
