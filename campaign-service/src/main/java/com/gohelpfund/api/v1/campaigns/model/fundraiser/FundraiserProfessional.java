package com.gohelpfund.api.v1.campaigns.model.fundraiser;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@JsonPropertyOrder({"job_title", "job_description", "company_name", "company_url"})
public class FundraiserProfessional {

    @Id
    @JsonIgnore
    @Column(name = "professional_id", nullable = false)
    private String professionalId;

    @JsonIgnore
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiserId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_url")
    private String companyUrl;

    public FundraiserProfessional(){
        this.jobTitle = "Engineer";
        this.jobDescription = "Engineering stuff 24/7";
        this.companyName = "iEngineer";
        this.companyUrl = "https://apple.com";
    }

    public String getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }

    public String getFundraiserId() {
        return fundraiserId;
    }

    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public FundraiserProfessional withId(String id){
        this.setProfessionalId(id);
        return this;
    }

    public FundraiserProfessional withFundraiserId(String fundraiserId){
        this.setFundraiserId(fundraiserId);
        return this;
    }
}
