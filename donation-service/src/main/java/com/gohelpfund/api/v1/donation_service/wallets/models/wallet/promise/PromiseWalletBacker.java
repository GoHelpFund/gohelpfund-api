package com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "promise_wallet_backers")
@JsonPropertyOrder({"fundraiser_id, total_amount"})
public class PromiseWalletBacker implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "backer_id", nullable = false)
    private String backerId;

    @JsonIgnore
    @Column(name = "promise_id", nullable = false)
    private String promiseId;

    @JsonProperty("fundraiser_id")
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiser_id;

    @JsonProperty("total_amount")
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "PromiseWalletBacker{" +
                "backerId='" + backerId + '\'' +
                ", promiseId='" + promiseId + '\'' +
                ", fundraiser_id='" + fundraiser_id + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}
