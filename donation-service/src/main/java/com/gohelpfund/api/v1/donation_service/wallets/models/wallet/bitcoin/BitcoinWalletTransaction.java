package com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bitcoin_wallet_transactions")
@JsonPropertyOrder({"id", "date", "type", "blockchain_transaction_id", "amount", "sender_name", "sender_address"})
public class BitcoinWalletTransaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @JsonIgnore
    @Column(name = "bitcoin_id", nullable = false)
    private String bitcoinId;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
    @Column(name = "transaction_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date transactionDate;

    @JsonProperty("type")
    @Column(name = "transaction_type")
    private String transactionType;

    @JsonProperty("blockchain_transaction_id")
    @Column(name = "blockchain_transaction_id")
    private String blockchainTransactionId;


    @JsonProperty("amount")
    @Column(name = "amount")
    private Integer amount;

    @JsonIgnore
    @Column(name = "sender_bitcoin_id")
    private String senderBitcoinId;

    @JsonIgnore
    @Column(name = "receiver_bitcoin_id")
    private String receiverBitcoinId;

    @JsonProperty("sender_name")
    @Column(name = "senderName")
    private String senderName;

    @JsonProperty("sender_address")
    @Column(name = "senderAddress")
    private String senderAddress;

    public BitcoinWalletTransaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBitcoinId() {
        return bitcoinId;
    }

    public void setBitcoinId(String bitcoinId) {
        this.bitcoinId = bitcoinId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getBlockchainTransactionId() {
        return blockchainTransactionId;
    }

    public void setBlockchainTransactionId(String blockchainTransactionId) {
        this.blockchainTransactionId = blockchainTransactionId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSenderBitcoinId() {
        return senderBitcoinId;
    }

    public void setSenderBitcoinId(String senderBitcoinId) {
        this.senderBitcoinId = senderBitcoinId;
    }

    public String getReceiverBitcoinId() {
        return receiverBitcoinId;
    }

    public void setReceiverBitcoinId(String receiverBitcoinId) {
        this.receiverBitcoinId = receiverBitcoinId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public BitcoinWalletTransaction withId(String transactionId){
        this.setTransactionId(transactionId);
        return this;
    }

    public BitcoinWalletTransaction withBitcoinId(String bitcoinId){
        this.setBitcoinId(bitcoinId);
        return this;
    }

    public BitcoinWalletTransaction withDate(Date transactionDate){
        this.setTransactionDate(transactionDate);
        return this;
    }

    public BitcoinWalletTransaction withType(String transactionType){
        this.setTransactionType(transactionType);
        return this;
    }

    public BitcoinWalletTransaction withBlockchainTransactionId(String blockchainTransactionId){
        this.setBlockchainTransactionId(blockchainTransactionId);
        return this;
    }

    public BitcoinWalletTransaction withAmount(Integer amount){
        this.setAmount(amount);
        return this;
    }

    public BitcoinWalletTransaction withReceiverBitcoinId(String receiverBitcoinId){
        this.setReceiverBitcoinId(receiverBitcoinId);
        return this;
    }

    public BitcoinWalletTransaction withSenderBitcoinId(String senderBitcoinId){
        this.setSenderBitcoinId(senderBitcoinId);
        return this;
    }

    public BitcoinWalletTransaction withSenderName(String senderName){
        this.setSenderName(senderName);
        return this;
    }

    public BitcoinWalletTransaction withSenderAddress(String senderAddress){
        this.setSenderAddress(senderAddress);
        return this;
    }

    @Override
    public String toString() {
        return "BitcoinWalletTransaction{" +
                "transactionId='" + transactionId + '\'' +
                ", bitcoinId='" + bitcoinId + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionType='" + transactionType + '\'' +
                ", blockchainTransactionId='" + blockchainTransactionId + '\'' +
                ", amount=" + amount +
                ", senderBitcoinId='" + senderBitcoinId + '\'' +
                ", receiverBitcoinId='" + receiverBitcoinId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", senderAddress='" + senderAddress + '\'' +
                '}';
    }
}