package com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin.bitcore;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BitcoinWalletAddress implements Serializable {
    @JsonProperty("confirmed")
    private Long confirmed;

    @JsonProperty("unconfirmed")
    private Long unconfirmed;

    @JsonProperty("balance")
    private Long balance;

    public Long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Long confirmed) {
        this.confirmed = confirmed;
    }

    public Long getUnconfirmed() {
        return unconfirmed;
    }

    public void setUnconfirmed(Long unconfirmed) {
        this.unconfirmed = unconfirmed;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
