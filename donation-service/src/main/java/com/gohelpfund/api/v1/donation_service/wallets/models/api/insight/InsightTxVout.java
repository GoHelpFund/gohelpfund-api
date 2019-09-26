package com.gohelpfund.api.v1.donation_service.wallets.models.api.insight;

public class InsightTxVout {
    String value;
    int n;
    InsightScriptPubKey scriptPubKey;

    public InsightTxVout(){

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public InsightScriptPubKey getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(InsightScriptPubKey scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }
}
