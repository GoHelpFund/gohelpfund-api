package com.gohelpfund.api.v1.campaign_service.campaigns.model.donation;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Donation implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer amount;

    public Donation() {
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
