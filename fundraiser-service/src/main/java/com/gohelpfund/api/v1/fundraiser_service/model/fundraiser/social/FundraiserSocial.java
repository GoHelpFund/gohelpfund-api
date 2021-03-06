package com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.social;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fundraiser_social")
@JsonPropertyOrder({"facebook", "twitter", "linkedin", "website", "other"})
public class FundraiserSocial implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "social_id", nullable = false)
    private String socialId;

    @JsonIgnore
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiserId;

    @Column(name = "facebook")
//    @URL
    private String facebook;

    @Column(name = "twitter")
//    @URL
    private String twitter;

    @Column(name = "linkedin")
//    @URL
    private String linkedin;

    @Column(name = "website")
//    @URL
    private String website;

    @Column(name = "other")
    private String other;

    public FundraiserSocial() {
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getFundraiserId() {
        return fundraiserId;
    }

    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public FundraiserSocial withId(String id){
        this.setSocialId(id);
        return this;
    }

    public FundraiserSocial withFundraiserId(String fundraiserId){
        this.setFundraiserId(fundraiserId);
        return this;
    }

    @Override
    public String toString() {
        return "FundraiserSocial{" +
                "socialId='" + socialId + '\'' +
                ", fundraiserId='" + fundraiserId + '\'' +
                ", facebook='" + facebook + '\'' +
                ", twitter='" + twitter + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", website='" + website + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
