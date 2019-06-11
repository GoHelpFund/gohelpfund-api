package com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.promise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Id;
import java.io.Serializable;

@JsonPropertyOrder({"fundraiser_id, total_amount"})
public class PromiseWalletBacker implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String backerId;

    @JsonIgnore
    private String promiseId;

    @JsonProperty("fundraiser_id")
    private String fundraiser_id;

    @JsonProperty("total_amount")
    private Integer totalAmount;

    public PromiseWalletBacker(){
    }

    public String getBackerId() {
        return backerId;
    }

    public void setBackerId(String backerId) {
        this.backerId = backerId;
    }

    public String getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(String promiseId) {
        this.promiseId = promiseId;
    }

    public String getFundraiser_id() {
        return fundraiser_id;
    }

    public void setFundraiser_id(String fundraiser_id) {
        this.fundraiser_id = fundraiser_id;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "PromiseWalletBacker{" +
                "backerId='" + backerId + '\'' +
                ", promiseId='" + promiseId + '\'' +
                ", fundraiser_id='" + fundraiser_id + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
