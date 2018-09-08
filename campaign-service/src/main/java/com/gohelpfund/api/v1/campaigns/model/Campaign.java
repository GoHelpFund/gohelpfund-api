package com.gohelpfund.api.v1.campaigns.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gohelpfund.api.v1.categories.model.Category;
import com.gohelpfund.api.v1.fundraisers.model.Fundraiser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "campaigns")
@JsonPropertyOrder({"campaignId", "campaignTitle", "campaignDescription", "amountGoal", "amountRaised",
        "expensesDescription", "location", "startDate", "endDate", "backers", "status",
        "category", "fundraiser", "resources"})
public class Campaign {
    @Id
    @JsonProperty("id")
    @Column(name = "campaign_id", nullable = false)
    private String campaignId;

    @JsonProperty("title")
    @Column(name = "campaign_title", nullable = false)
    private String campaignTitle;

    @JsonProperty("description")
    @Column(name = "campaign_description", nullable = false)
    private String campaignDescription;

    @JsonProperty("amount_goal")
    @Column(name = "amount_goal", nullable = false)
    private Integer amountGoal;

    @JsonProperty("amount_raised")
    @Column(name = "amount_raised")
    private Integer amountRaised;

    @JsonProperty("expenses_description")
    @Column(name = "expenses_description", nullable = false)
    private String expensesDescription;

    @Column(name = "location", nullable = false)
    private String location;

    @JsonProperty("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date startDate;

    @JsonProperty("end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
    @Column(name = "end_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date endDate;

    @Column(name = "backers")
    private int backers;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private CampaignStatus status;

    @ManyToOne
    @JoinColumn(name = "fundraiser_id")
    private Fundraiser fundraiser;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonProperty("media_resources")
    @JoinColumn(name = "campaign_id")
    @ElementCollection(targetClass = CampaignMediaResource.class)
    private List<CampaignMediaResource> resources;

    public Campaign() {

    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public Fundraiser getFundraiser() {
        return fundraiser;
    }

    public void setFundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    public String getCampaignDescription() {
        return campaignDescription;
    }

    public void setCampaignDescription(String campaignDescription) {
        this.campaignDescription = campaignDescription;
    }

    public Integer getAmountGoal() {
        return amountGoal;
    }

    public void setAmountGoal(Integer amountGoal) {
        this.amountGoal = amountGoal;
    }

    public Integer getAmountRaised() {
        return amountRaised;
    }

    public void setAmountRaised(Integer amountRaised) {
        this.amountRaised = amountRaised;
    }

    public String getExpensesDescription() {
        return expensesDescription;
    }

    public void setExpensesDescription(String expensesDescription) {
        this.expensesDescription = expensesDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<CampaignMediaResource> getResources() {
        return resources;
    }

    public void setResources(List<CampaignMediaResource> resources) {
        this.resources = resources;
    }

    public int getBackers() {
        return backers;
    }

    public void setBackers(int backers) {
        this.backers = backers;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public Campaign withId(String campaignId) {
        this.setCampaignId(campaignId);
        return this;
    }

    public Campaign withStatus(CampaignStatus status){
        this.setStatus(status);
        return this;
    }

    public Campaign withMediaResources(List<CampaignMediaResource> resources) {
        if (resources != null) {
            this.setResources(resources);
        }
        return this;
    }

    public Campaign withFundraiser(Fundraiser fundraiser){
        this.setFundraiser(fundraiser);
        return this;
    }

}
