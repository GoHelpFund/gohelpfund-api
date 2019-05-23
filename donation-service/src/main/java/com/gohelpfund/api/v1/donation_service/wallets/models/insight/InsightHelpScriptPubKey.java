package com.gohelpfund.api.v1.donation_service.wallets.models.insight;

import java.util.List;

public class InsightHelpScriptPubKey {
    private String hex;

    private List<String> addresses;

    public InsightHelpScriptPubKey(){

    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
