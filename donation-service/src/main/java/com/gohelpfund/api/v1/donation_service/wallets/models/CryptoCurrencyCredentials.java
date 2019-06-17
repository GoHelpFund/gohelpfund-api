package com.gohelpfund.api.v1.donation_service.wallets.models;

public class CryptoCurrencyCredentials {

    private String publicKey;
    private String privateKey;

    public CryptoCurrencyCredentials(){

    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
