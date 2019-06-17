package com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.help.HelpWalletDetails;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.promise.PromiseWalletDetails;

import javax.persistence.Transient;
import java.io.Serializable;

@JsonPropertyOrder({"id", "help", "promise"})
public class Wallet implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    @JsonIgnore
    @JsonProperty("entity_id")
    private String entityId;

    @JsonProperty("help")
    @Transient
    private HelpWalletDetails helpWallet;

    @JsonProperty("promise")
    @Transient
    private PromiseWalletDetails promiseWallet;

    public Wallet() {
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

    public HelpWalletDetails getHelpWallet() {
        return helpWallet;
    }

    public void setHelpWallet(HelpWalletDetails helpWallet) {
        this.helpWallet = helpWallet;
    }

    public PromiseWalletDetails getPromiseWallet() {
        return promiseWallet;
    }

    public void setPromiseWallet(PromiseWalletDetails promiseWallet) {
        this.promiseWallet = promiseWallet;
    }

    public Wallet withId(String walletId){
        this.setId(walletId);
        return this;
    }

    public Wallet withEntityId(String entityId){
        this.setEntityId(entityId);
        return this;
    }

    public Wallet withHelpWalletDetails(HelpWalletDetails helpWallet){
        this.setHelpWallet(helpWallet);
        return this;
    }

    public Wallet withPromiseWalletDetails(PromiseWalletDetails promiseWallet){
        this.setPromiseWallet(promiseWallet);
        return this;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id='" + id + '\'' +
                ", entityId='" + entityId + '\'' +
                ", helpWallet=" + helpWallet +
                ", promiseWallet=" + promiseWallet +
                '}';
    }
}