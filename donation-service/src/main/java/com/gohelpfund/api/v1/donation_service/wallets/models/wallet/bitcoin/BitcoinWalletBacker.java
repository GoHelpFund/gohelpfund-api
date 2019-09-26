package com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bitcoin_wallet_backers")
@JsonPropertyOrder({"fundraiser_id"})
public class BitcoinWalletBacker implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "backer_id", nullable = false)
    private String backerId;

    @JsonIgnore
    @Column(name = "bitcoin_id", nullable = false)
    private String bitcoinId;

    @JsonProperty("fundraiser_id")
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiser_id;

    public BitcoinWalletBacker(){
    }

    public String getBackerId() {
        return backerId;
    }

    public void setBackerId(String backerId) {
        this.backerId = backerId;
    }

    public String getBitcoinId() {
        return bitcoinId;
    }

    public void setBitcoinId(String bitcoinId) {
        this.bitcoinId = bitcoinId;
    }

    public String getFundraiser_id() {
        return fundraiser_id;
    }

    public void setFundraiser_id(String fundraiser_id) {
        this.fundraiser_id = fundraiser_id;
    }

    @Override
    public String toString() {
        return "BitcoinWalletBacker{" +
                "backerId='" + backerId + '\'' +
                ", bitcoinId='" + bitcoinId + '\'' +
                ", fundraiser_id='" + fundraiser_id + '\'' +
                '}';
    }
}
