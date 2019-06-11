package com.gohelpfund.api.v1.donation_service.wallets.models.wallet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise.PromiseWalletDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "wallets")
@JsonPropertyOrder({"id", "entity_id", "type", "help", "promise"})
public class Wallet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    @Column(name = "wallet_id", nullable = false)
    private String id;

    @JsonIgnore
    @Column(name = "help_id")
    private String helpId;

    @JsonIgnore
    @Column(name = "promise_id")
    private String promiseId;

    @JsonProperty("entity_id")
    @Column(name = "entity_id", nullable = false)
    @NotNull
    private String entityId;

    @JsonProperty("type")
    @Column(name = "type", nullable = false)
    @NotNull
    private String type;

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

    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Wallet withWalletId(String walletId){
        this.setId(walletId);
        return this;
    }

    public Wallet withHelpId(String helpId){
        this.setHelpId(helpId);
        return this;
    }

    public Wallet withPromiseId(String promiseId){
        this.setPromiseId(promiseId);
        return this;
    }

    public Wallet withEntityId(String entityId){
        this.setEntityId(entityId);
        return this;
    }

    public Wallet withType(String type){
        this.setType(type);
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
                ", helpId='" + helpId + '\'' +
                ", promiseId='" + promiseId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", type='" + type + '\'' +
                ", helpWallet=" + helpWallet +
                ", promiseWallet=" + promiseWallet +
                '}';
    }
}