package com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.professional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fundraiser_professional")
@JsonPropertyOrder({"job_title", "job_description", "company_name", "company_url"})
public class FundraiserProfessional implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "professional_id", nullable = false)
    private String professionalId;

    @JsonIgnore
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiserId;

    @Column(name = "job_title")
    @JsonProperty("job_title")
    private String job_title;

    @Column(name = "job_description")
    @JsonProperty("job_description")
    private String job_description;

    @Column(name = "company_name")
    @JsonProperty("company_name")
    private String company_name;

    @Column(name = "company_url")
    @JsonProperty("company_url")
//    @URL
    private String company_url;

    public FundraiserProfessional() {
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

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_url() {
        return company_url;
    }

    public void setCompany_url(String company_url) {
        this.company_url = company_url;
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
                ", job_title='" + job_title + '\'' +
                ", job_description='" + job_description + '\'' +
                ", company_name='" + company_name + '\'' +
                ", company_url='" + company_url + '\'' +
                '}';
    }
}
