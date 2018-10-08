package com.gohelpfund.api.v1.fundraisers.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity
@Table(name = "fundraisers")
@JsonPropertyOrder({"id", "name", "age", "profile_image_url", "status", "social", "professional"})
public class Fundraiser {
    @Id
    @JsonProperty("id")
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiserId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @JsonProperty("profile_image_url")
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @ManyToOne
    @JoinColumn(name = "social_id")
    private FundraiserSocial social;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private FundraiserProfessional professional;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private FundraiserStatus status;

    public Fundraiser(){
        this.name = "Zach";
        this.age = 29;
        this.profileImageUrl = "https://s3.eu-central-1.amazonaws.com/gohelpfund-resources/generic-user.jpg";
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public void setSocial(FundraiserSocial social) {
        this.social = social;
    }

    public FundraiserStatus getStatus() {
        return status;
    }

    public void setStatus(FundraiserStatus status) {
        this.status = status;
    }

    public Fundraiser withId(String fundraiserId){
        this.setFundraiserId(fundraiserId);
        return this;
    }

    public Fundraiser withSocial(FundraiserSocial social){
        this.setSocial(social);
        return this;
    }

    public Fundraiser withProfessional(FundraiserProfessional professional){
        this.setProfessional(professional);
        return this;
    }

    public Fundraiser withStatus(FundraiserStatus status){
        this.setStatus(status);
        return this;
    }

}
