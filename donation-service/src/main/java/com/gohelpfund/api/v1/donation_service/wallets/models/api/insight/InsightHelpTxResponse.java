package com.gohelpfund.api.v1.donation_service.wallets.models.api.insight;

public class InsightHelpTxResponse {
    private String txid;

    public InsightHelpTxResponse(){

    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }
}
