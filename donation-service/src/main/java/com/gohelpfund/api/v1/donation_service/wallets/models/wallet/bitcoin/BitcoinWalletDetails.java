package com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "bitcoin_wallet_details")
@JsonPropertyOrder({"address", "balance", "transactions"})
public class BitcoinWalletDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "bitcoin_id", nullable = false)
    private String bitcoinId;

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
    private List<BitcoinWalletTransaction> transactions;

    @JsonProperty("backers")
    @Transient
    private List<BitcoinWalletBacker> backers;

    public BitcoinWalletDetails() {
    }

    public String getBitcoinId() {
        return bitcoinId;
    }

    public void setBitcoinId(String bitcoinId) {
        this.bitcoinId = bitcoinId;
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

    public BitcoinWalletDetails withId(String bitcoinId){
        this.setBitcoinId(bitcoinId);
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

    @Override
    public String toString() {
        return "BitcoinWalletDetails{" +
                "bitcoinId='" + bitcoinId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                ", backers=" + backers +
                '}';
    }
}
