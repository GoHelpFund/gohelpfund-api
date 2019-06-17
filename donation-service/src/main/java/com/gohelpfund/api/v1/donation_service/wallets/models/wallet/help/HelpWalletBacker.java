package com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "help_wallet_backers")
@JsonPropertyOrder({"fundraiser_id"})
public class HelpWalletBacker implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "backer_id", nullable = false)
    private String backerId;

    @JsonIgnore
    @Column(name = "help_id", nullable = false)
    private String helpId;

    @JsonProperty("fundraiser_id")
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiser_id;

    public HelpWalletBacker(){
    }

    public String getBackerId() {
        return backerId;
    }

    public void setBackerId(String backerId) {
        this.backerId = backerId;
    }

    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
    }

    public String getFundraiser_id() {
        return fundraiser_id;
    }

    public void setFundraiser_id(String fundraiser_id) {
        this.fundraiser_id = fundraiser_id;
    }

    @Override
    public String toString() {
        return "PromiseWalletBacker{" +
                "backerId='" + backerId + '\'' +
                ", helpId='" + helpId + '\'' +
                ", fundraiser_id='" + fundraiser_id + '\'' +
                '}';
    }
}
