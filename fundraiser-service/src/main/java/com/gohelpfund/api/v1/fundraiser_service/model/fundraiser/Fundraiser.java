package com.gohelpfund.api.v1.fundraiser_service.model.fundraiser;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.professional.FundraiserProfessional;
import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.social.FundraiserSocial;
import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.status.FundraiserStatus;
import com.gohelpfund.api.v1.fundraiser_service.model.wallet.Wallet;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fundraisers")
@JsonPropertyOrder({"id", "name", "age", "profile_image_url", "status", "social", "professional"})
public class Fundraiser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiserId;

    @JsonIgnore
    @Column(name ="wallet_id", nullable = false)
    private String walletId;

    @Column(name = "name")
//    @NotBlank
//    @Size(min=2, max=40)
    private String name;

    @Column(name = "age")
//    @NotNull
//    @Range(min = 18, max = 120)
    private Integer age;

    @JsonProperty("profile_image_url")
    @Column(name = "profile_image_url")
//    @URL
//    @Size(min = 10, max = 256)
    private String profileImageUrl;

    @ManyToOne
    @JoinColumn(name = "social_id")
    private FundraiserSocial social;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private FundraiserProfessional professional;

    @Transient
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private FundraiserStatus status;

    public Fundraiser() {
    }

    public String getFundraiserId() {
        return fundraiserId;
    }

    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public FundraiserSocial getSocial() {
        return social;
    }

    public FundraiserProfessional getProfessional() {
        return professional;
    }

    public void setProfessional(FundraiserProfessional professional) {
        this.professional = professional;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setSocial(FundraiserSocial social) {
        this.social = social;
    }

    public FundraiserStatus getStatus() {
        return status;
    }

    public void setStatus(FundraiserStatus status) {
        this.status = status;
    }

    public Fundraiser withId(String fundraiserId) {
        this.setFundraiserId(fundraiserId);
        return this;
    }

    public Fundraiser withWalletId(String walletId){
        this.setWalletId(walletId);
        return this;
    }

    public Fundraiser withSocial(FundraiserSocial social) {
        this.setSocial(social);
        return this;
    }

    public Fundraiser withProfessional(FundraiserProfessional professional) {
        this.setProfessional(professional);
        return this;
    }

    public Fundraiser withWallet(Wallet wallet){
        this.setWallet(wallet);
        return this;
    }

    public Fundraiser withStatus(FundraiserStatus status) {
        this.setStatus(status);
        return this;
    }

    @Override
    public String toString() {
        return "Fundraiser{" +
                "fundraiserId='" + fundraiserId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", social=" + social +
                ", professional=" + professional +
                ", status=" + status +
                '}';
    }
}