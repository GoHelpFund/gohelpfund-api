package com.gohelpfund.api.v1.donation_service.wallets.models.api.insight;

import java.util.List;

public class InsightAddr {

    private long balanceSat;
    private long unconfirmedBalanceSat;
    private List<String> transactions;

    public InsightAddr() {

    }

    public long getBalanceSat() {
        return balanceSat;
    }

    public void setBalanceSat(long balanceSat) {
        this.balanceSat = balanceSat;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    public long getUnconfirmedBalanceSat() {
        return unconfirmedBalanceSat;
    }

    public void setUnconfirmedBalanceSat(long unconfirmedBalanceSat) {
        this.unconfirmedBalanceSat = unconfirmedBalanceSat;
    }
}
