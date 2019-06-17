package com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "help_wallet_transactions")
@JsonPropertyOrder({"id", "date", "type", "blockchain_transaction_id", "amount", "sender_name", "sender_address"})
public class HelpWalletTransaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @JsonIgnore
    @Column(name = "help_id", nullable = false)
    private String helpId;

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
    @Column(name = "sender_help_id")
    private String senderHelpId;

    @JsonIgnore
    @Column(name = "receiver_help_id")
    private String receiverHelpId;

    @JsonProperty("sender_name")
    @Column(name = "senderName")
    private String senderName;

    @JsonProperty("sender_address")
    @Column(name = "senderAddress")
    private String senderAddress;

    public HelpWalletTransaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
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

    public String getSenderHelpId() {
        return senderHelpId;
    }

    public void setSenderHelpId(String senderHelpId) {
        this.senderHelpId = senderHelpId;
    }

    public String getReceiverHelpId() {
        return receiverHelpId;
    }

    public void setReceiverHelpId(String receiverHelpId) {
        this.receiverHelpId = receiverHelpId;
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

    public HelpWalletTransaction withId(String transactionId){
        this.setTransactionId(transactionId);
        return this;
    }

    public HelpWalletTransaction withHelpId(String helpId){
        this.setHelpId(helpId);
        return this;
    }

    public HelpWalletTransaction withDate(Date transactionDate){
        this.setTransactionDate(transactionDate);
        return this;
    }

    public HelpWalletTransaction withType(String transactionType){
        this.setTransactionType(transactionType);
        return this;
    }

    public HelpWalletTransaction withBlockchainTransactionId(String blockchainTransactionId){
        this.setBlockchainTransactionId(blockchainTransactionId);
        return this;
    }

    public HelpWalletTransaction withAmount(Integer amount){
        this.setAmount(amount);
        return this;
    }

    public HelpWalletTransaction withReceiverHelpId(String receiverHelpId){
        this.setReceiverHelpId(receiverHelpId);
        return this;
    }

    public HelpWalletTransaction withSenderHelpId(String senderHelpId){
        this.setSenderHelpId(senderHelpId);
        return this;
    }

    public HelpWalletTransaction withSenderName(String senderName){
        this.setSenderName(senderName);
        return this;
    }

    public HelpWalletTransaction withSenderAddress(String senderAddress){
        this.setSenderAddress(senderAddress);
        return this;
    }

    @Override
    public String toString() {
        return "PromiseWalletTransaction{" +
                "transactionId='" + transactionId + '\'' +
                ", helpId='" + helpId + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionType='" + transactionType + '\'' +
                ", blockchainTransactionId='" + blockchainTransactionId + '\'' +
                ", amount=" + amount +
                ", senderHelpId='" + senderHelpId + '\'' +
                ", receiverHelpId='" + receiverHelpId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", senderAddress='" + senderAddress + '\'' +
                '}';
    }
}