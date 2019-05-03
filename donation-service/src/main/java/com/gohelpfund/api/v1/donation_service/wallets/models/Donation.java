package com.gohelpfund.api.v1.donation_service.wallets.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"entity_id", "type", "amount"})
public class Donation implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("entity_id")
    private String entity_id;

    private Integer amount;

    private String type;

    public Donation() {
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
