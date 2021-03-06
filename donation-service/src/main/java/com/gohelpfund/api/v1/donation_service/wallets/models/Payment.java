package com.gohelpfund.api.v1.donation_service.wallets.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"entity_id", "amount"})
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("entity_id")
    private String entityId;

    private Double amount;

    public Payment() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
