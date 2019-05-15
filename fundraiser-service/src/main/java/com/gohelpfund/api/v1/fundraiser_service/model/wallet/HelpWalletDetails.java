package com.gohelpfund.api.v1.fundraiser_service.model.wallet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Transient;
import java.util.List;

@JsonPropertyOrder({"address", "balance", "transactions"})
public class HelpWalletDetails {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String id;

    @JsonIgnore
    private String entityId;

    @JsonProperty("address")
    private String publicKey;

    @JsonIgnore
    private String privateKey;

    @JsonProperty("balance")
    private Integer balance;

    @JsonProperty("transactions")
    @Transient
    private List<HelpWalletTransaction> transactions;

    @JsonProperty("backers")
    @Transient
    private List<HelpWalletBacker> backers;

    public HelpWalletDetails() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<HelpWalletTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<HelpWalletTransaction> transactions) {
        this.transactions = transactions;
    }

    public List<HelpWalletBacker> getBackers() {
        return backers;
    }

    public void setBackers(List<HelpWalletBacker> backers) {
        this.backers = backers;
    }

    public HelpWalletDetails withId(String id){
        this.setId(id);
        return this;
    }

    public HelpWalletDetails withEnityId(String entityId){
        this.setEntityId(entityId);
        return this;
    }

    public HelpWalletDetails withPublicKey(String publicKey){
        this.setPublicKey(publicKey);
        return this;
    }

    public HelpWalletDetails withPrivateKey(String privateKey){
        this.setPrivateKey(privateKey);
        return this;
    }

    public HelpWalletDetails withBalance(Integer balance){
        this.setBalance(balance);
        return this;
    }

}
