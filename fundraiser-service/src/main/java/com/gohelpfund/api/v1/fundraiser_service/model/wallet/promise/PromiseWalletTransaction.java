package com.gohelpfund.api.v1.fundraiser_service.model.wallet.promise;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
    private String transactionId;

    @JsonIgnore
    private String promiseId;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date transactionDate;

    @JsonProperty("type")
    private String transactionType;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("sender_name")
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public PromiseWalletTransaction withId(String transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public PromiseWalletTransaction withHelpId(String helpId) {
        this.setPromiseId(helpId);
        return this;
    }

    public PromiseWalletTransaction withDate(Date transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public PromiseWalletTransaction withType(String transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public PromiseWalletTransaction withAmount(Integer amount) {
        this.setAmount(amount);
        return this;
    }

    public PromiseWalletTransaction withSenderName(String senderName) {
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
                ", senderName='" + senderName + '\'' +
                '}';
    }
}