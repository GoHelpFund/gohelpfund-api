package com.gohelpfund.api.v1.fundraiser_service.model.wallet.bitcoin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@JsonPropertyOrder({"id", "date", "type", "blockchainTransactionId", "amount", "senderBitcoinId", "receiverBitcoinId", "sender_name", "sender_address"})
public class BitcoinWalletTransaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    private String id;

    @JsonIgnore
    private String bitcoinId;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
    private Date date;

    @JsonProperty("type")
    private String type;

    @JsonProperty("blockchain_transaction_id")
    private String blockchainTransactionId;


    @JsonProperty("amount")
    private Double amount;

    @JsonIgnore
    @JsonProperty("sender_bitcoin_id")
    private String senderBitcoinId;

    @JsonIgnore
    @JsonProperty("receiver_bitcoin_id")
    private String receiverBitcoinId;

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("sender_address")
    private String senderAddress;

    public BitcoinWalletTransaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBitcoinId() {
        return bitcoinId;
    }

    public void setBitcoinId(String bitcoinId) {
        this.bitcoinId = bitcoinId;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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
}