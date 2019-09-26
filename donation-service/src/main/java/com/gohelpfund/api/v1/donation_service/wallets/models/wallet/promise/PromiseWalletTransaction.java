package com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise;

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
@Table(name = "promise_wallet_transactions")
@JsonPropertyOrder({"id", "date", "type", "amount", "sender_name"})
public class PromiseWalletTransaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @JsonIgnore
    @Column(name = "promise_id", nullable = false)
    private String promiseId;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
    @Column(name = "transaction_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date transactionDate;

    @JsonProperty("type")
    @Column(name = "transaction_type")
    private String transactionType;

    @JsonProperty("amount")
    @Column(name = "amount")
    private Double amount;

    @JsonIgnore
    @Column(name = "sender_promise_id")
    private String senderPromiseId;

    @JsonIgnore
    @Column(name = "receiver_promise_id")
    private String receiverPromiseId;

    @JsonProperty("sender_name")
    @Column(name = "senderName")
    private String senderName;

    public PromiseWalletTransaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(String promiseId) {
        this.promiseId = promiseId;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSenderPromiseId() {
        return senderPromiseId;
    }

    public void setSenderPromiseId(String senderPromiseId) {
        this.senderPromiseId = senderPromiseId;
    }

    public String getReceiverPromiseId() {
        return receiverPromiseId;
    }

    public void setReceiverPromiseId(String receiverPromiseId) {
        this.receiverPromiseId = receiverPromiseId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public PromiseWalletTransaction withId(String transactionId){
        this.setTransactionId(transactionId);
        return this;
    }

    public PromiseWalletTransaction withHelpId(String helpId){
        this.setPromiseId(helpId);
        return this;
    }

    public PromiseWalletTransaction withDate(Date transactionDate){
        this.setTransactionDate(transactionDate);
        return this;
    }

    public PromiseWalletTransaction withType(String transactionType){
        this.setTransactionType(transactionType);
        return this;
    }

    public PromiseWalletTransaction withAmount(Double amount){
        this.setAmount(amount);
        return this;
    }

    public PromiseWalletTransaction withReceiverHelpId(String receiverHelpId){
        this.setReceiverPromiseId(receiverHelpId);
        return this;
    }

    public PromiseWalletTransaction withSenderHelpId(String senderHelpId){
        this.setSenderPromiseId(senderHelpId);
        return this;
    }

    public PromiseWalletTransaction withSenderName(String senderName){
        this.setSenderName(senderName);
        return this;
    }

    @Override
    public String toString() {
        return "PromiseWalletTransaction{" +
                "transactionId='" + transactionId + '\'' +
                ", promiseId='" + promiseId + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", senderPromiseId='" + senderPromiseId + '\'' +
                ", receiverPromiseId='" + receiverPromiseId + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}