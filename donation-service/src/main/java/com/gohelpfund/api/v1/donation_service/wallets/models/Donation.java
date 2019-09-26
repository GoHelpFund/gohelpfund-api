package com.gohelpfund.api.v1.donation_service.wallets.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"entity_id", "entity_name", "type", "amount"})
public class Donation implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("entity_id")
    private String entity_id;

    @JsonProperty("entity_name")
    private String entity_name;

    private Double amount;

    private String type;

    public Donation() {
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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
