package com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction;

public enum OutputType {

    CUSTOM("Custom"), DONATE("Donate"), CHANGE("Change"), UNSPENDABLE("Unspendable");

    private String desc;

    OutputType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
