package com.gohelpfund.api.v1.donation_service.wallets.models.api.insight;

public class InsightTxResponse {
    private String txid;

    public InsightTxResponse(){

    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }
}
