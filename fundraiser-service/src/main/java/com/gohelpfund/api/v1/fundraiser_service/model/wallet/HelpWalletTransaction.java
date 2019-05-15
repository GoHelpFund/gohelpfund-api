package com.gohelpfund.api.v1.fundraiser_service.model.wallet;

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

@JsonPropertyOrder({"id", "date", "type", "blockchainTransactionId", "amount", "senderHelpId", "receiverHelpId",
        "sender_name", "sender_address"})
public class HelpWalletTransaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    private String id;

    @JsonIgnore
    private String helpId;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date date;

    @JsonProperty("type")
    private String type;

    @JsonProperty("blockchain_transaction_id")
    private String blockchainTransactionId;

    @JsonProperty("amount")
    private Integer amount;

    @JsonIgnore
    @JsonProperty("sender_help_id")
    private String senderHelpId;

    @JsonIgnore
    @JsonProperty("receiver_help_id")
    private String receiverHelpId;

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("sender_address")
    private String senderAddress;

    public HelpWalletTransaction() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}