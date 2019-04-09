package com.gohelpfund.api.v1.fundraisers.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fundraisers")
@JsonPropertyOrder({"id", "name", "age", "profile_image_url", "status", "social", "professional"})
public class Fundraiser {
    @Id
    @JsonProperty("id")
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiserId;

    @Column(name = "name")
    @NotBlank
    @Size(min=2, max=40)
    private String name;

    @Column(name = "age")
    @NotNull
    @Range(min = 18, max = 120)
    private Integer age;

    @JsonProperty("profile_image_url")
    @Column(name = "profile_image_url")
    @URL
    @Size(min = 10, max = 256)
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

    public Fundraiser() {
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

    public Fundraiser withSocial(FundraiserSocial social) {
        this.setSocial(social);
        return this;
    }

    public Fundraiser withProfessional(FundraiserProfessional professional) {
        this.setProfessional(professional);
        return this;
    }

    public Fundraiser withStatus(FundraiserStatus status) {
        this.setStatus(status);
        return this;
    }

}
