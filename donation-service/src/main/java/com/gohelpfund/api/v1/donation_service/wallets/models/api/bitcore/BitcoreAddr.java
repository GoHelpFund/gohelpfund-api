package com.gohelpfund.api.v1.donation_service.wallets.models.api.bitcore;

import java.util.List;

public class BitcoreAddr {

    private long confirmed;
    private long unconfirmed;
    private long balance;

    public BitcoreAddr() {

    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getUnconfirmed() {
        return unconfirmed;
    }

    public void setUnconfirmed(long unconfirmed) {
        this.unconfirmed = unconfirmed;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
