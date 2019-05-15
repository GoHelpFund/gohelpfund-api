package com.gohelpfund.api.v1.donation_service.wallets.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "wallets")
@JsonPropertyOrder({"id", "entity_id", "type", "help"})
public class Wallet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    @Column(name = "wallet_id", nullable = false)
    private String id;

    @JsonProperty("entity_id")
    @Column(name = "entity_id", nullable = false)
    @NotNull
    private String entityId;

    @JsonProperty("type")
    @Column(name = "type", nullable = false)
    @NotNull
    private String type;

    @JsonProperty("help")
    @ManyToOne
    @JoinColumn(name = "help_id")
    private HelpWalletDetails helpWallet;

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

    public Wallet withWalletId(String walletId){
        this.setId(walletId);
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

    @Override
    public String toString() {
        return "Wallet{" +
                "id='" + id + '\'' +
                ", entityId='" + entityId + '\'' +
                ", type='" + type + '\'' +
                ", helpWallet=" + helpWallet +
                '}';
    }
}