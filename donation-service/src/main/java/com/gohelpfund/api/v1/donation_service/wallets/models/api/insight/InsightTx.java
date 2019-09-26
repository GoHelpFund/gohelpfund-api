package com.gohelpfund.api.v1.donation_service.wallets.models.api.insight;

import javax.persistence.Transient;
import java.util.List;

public class InsightTx {
    private String txid;

    @Transient
    private List<InsightTxVout> vout;

    public InsightTx(){

    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public List<InsightTxVout> getVout() {
        return vout;
    }

    public void setVout(List<InsightTxVout> vout) {
        this.vout = vout;
    }
}
