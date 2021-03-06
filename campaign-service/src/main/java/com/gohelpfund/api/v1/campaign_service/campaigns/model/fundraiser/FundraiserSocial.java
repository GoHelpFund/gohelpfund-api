package com.gohelpfund.api.v1.campaign_service.campaigns.model.fundraiser;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;


@JsonPropertyOrder({"facebook", "twitter", "linkedin", "website", "other"})
public class FundraiserSocial implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String socialId;

    @JsonIgnore
    private String fundraiserId;

    @JsonProperty("facebook")
    private String facebook;

    @JsonProperty("twitter")
    private String twitter;

    @JsonProperty("linkedin")
    private String linkedin;

    @JsonProperty("website")
    private String website;

    @JsonProperty("other")
    private String other;

    public FundraiserSocial(){
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
