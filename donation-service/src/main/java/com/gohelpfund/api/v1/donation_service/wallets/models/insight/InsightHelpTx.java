package com.gohelpfund.api.v1.donation_service.wallets.models.insight;

import javax.persistence.Transient;
import java.util.List;

public class InsightHelpTx {
    private String txid;

    @Transient
    private List<InsightHelpTxVout> vout;

    public InsightHelpTx(){

    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public List<InsightHelpTxVout> getVout() {
        return vout;
    }

    public void setVout(List<InsightHelpTxVout> vout) {
        this.vout = vout;
    }
}
