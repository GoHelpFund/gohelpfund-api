package com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "help_wallet_details")
@JsonPropertyOrder({"address", "balance", "transactions"})
public class HelpWalletDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "help_id", nullable = false)
    private String helpId;

    @JsonIgnore
    @Column(name = "entity_id", nullable = false)
    private String entityId;

    @JsonProperty("address")
    @Column(name = "public_key")
    private String publicKey;

    @JsonIgnore
    @Column(name = "private_key")
    private String privateKey;

    @JsonProperty("balance")
    @Column(name = "balance")
    private Double balance;

    @JsonProperty("transactions")
    @Transient
    private List<HelpWalletTransaction> transactions;

    @JsonProperty("backers")
    @Transient
    private List<HelpWalletBacker> backers;

    public HelpWalletDetails() {
    }

    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
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

    public HelpWalletDetails withId(String helpId){
        this.setHelpId(helpId);
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

    public HelpWalletDetails withBalance(Double balance){
        this.setBalance(balance);
        return this;
    }

    @Override
    public String toString() {
        return "HelpWalletDetails{" +
                "helpId='" + helpId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                ", backers=" + backers +
                '}';
    }
}
