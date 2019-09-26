package com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.bitcoin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Transient;
import java.util.List;

@JsonPropertyOrder({"address", "balance", "transactions"})
public class BitcoinWalletDetails {
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
    private Double balance;

    @JsonProperty("transactions")
    @Transient
    private List<BitcoinWalletTransaction> transactions;

    @JsonProperty("backers")
    @Transient
    private List<BitcoinWalletBacker> backers;

    public BitcoinWalletDetails() {
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<BitcoinWalletTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BitcoinWalletTransaction> transactions) {
        this.transactions = transactions;
    }

    public List<BitcoinWalletBacker> getBackers() {
        return backers;
    }

    public void setBackers(List<BitcoinWalletBacker> backers) {
        this.backers = backers;
    }

    public BitcoinWalletDetails withId(String id){
        this.setId(id);
        return this;
    }

    public BitcoinWalletDetails withEnityId(String entityId){
        this.setEntityId(entityId);
        return this;
    }

    public BitcoinWalletDetails withPublicKey(String publicKey){
        this.setPublicKey(publicKey);
        return this;
    }

    public BitcoinWalletDetails withPrivateKey(String privateKey){
        this.setPrivateKey(privateKey);
        return this;
    }

    public BitcoinWalletDetails withBalance(Double balance){
        this.setBalance(balance);
        return this;
    }

}
