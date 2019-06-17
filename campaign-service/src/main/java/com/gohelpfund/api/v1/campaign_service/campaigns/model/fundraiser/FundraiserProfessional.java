package com.gohelpfund.api.v1.campaign_service.campaigns.model.fundraiser;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;


@JsonPropertyOrder({"job_title", "job_description", "company_name", "company_url"})
public class FundraiserProfessional implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String professionalId;

    @JsonIgnore
    private String fundraiserId;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("job_description")
    private String jobDescription;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("company_url")
    private String companyUrl;

    public FundraiserProfessional(){
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

    @Override
    public String toString() {
        return "FundraiserProfessional{" +
                "professionalId='" + professionalId + '\'' +
                ", fundraiserId='" + fundraiserId + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                '}';
    }
}
