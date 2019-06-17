package com.gohelpfund.api.v1.fundraiser_service.model.wallet.promise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "promise_wallet_details")
@JsonPropertyOrder({"balance", "transactions"})
public class PromiseWalletDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    private String promiseId;

    @JsonIgnore
    private String entityId;

    @JsonProperty("balance")
    private Integer balance;

    @JsonProperty("transactions")
    @Transient
    private List<PromiseWalletTransaction> transactions;

    @JsonProperty("backers")
    @Transient
    private List<PromiseWalletBacker> backers;

    public PromiseWalletDetails() {
    }


    public String getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(String promiseId) {
        this.promiseId = promiseId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<PromiseWalletTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<PromiseWalletTransaction> transactions) {
        this.transactions = transactions;
    }

    public List<PromiseWalletBacker> getBackers() {
        return backers;
    }

    public void setBackers(List<PromiseWalletBacker> backers) {
        this.backers = backers;
    }

    public PromiseWalletDetails withId(String promiseId){
        this.setPromiseId(promiseId);
        return this;
    }

    public PromiseWalletDetails withEnityId(String entityId){
        this.setEntityId(entityId);
        return this;
    }

    public PromiseWalletDetails withBalance(Integer balance){
        this.setBalance(balance);
        return this;
    }

    @Override
    public String toString() {
        return "PromiseWalletDetails{" +
                "promiseId='" + promiseId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                ", backers=" + backers +
                '}';
    }
}
