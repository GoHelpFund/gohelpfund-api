package com.gohelpfund.api.v1.donation_service.wallets.models.api.insight;

public class InsightHelpTxVout {
    String value;
    int n;
    InsightHelpScriptPubKey scriptPubKey;

    public InsightHelpTxVout(){

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

    public InsightHelpScriptPubKey getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(InsightHelpScriptPubKey scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }
}
