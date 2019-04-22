package com.gohelpfund.api.v1.campaigns.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gohelpfund.api.v1.campaigns.model.category.Category;
import com.gohelpfund.api.v1.campaigns.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.campaigns.model.mediaresource.CampaignMediaResource;
import com.gohelpfund.api.v1.campaigns.model.status.CampaignStatus;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "campaigns")
@JsonPropertyOrder({"campaignId", "campaignTitle", "campaignDescription", "amountGoal", "amountRaised",
        "expensesDescription", "location", "startDate", "endDate", "backers", "status",
        "category", "fundraiser", "resources"})
public class Campaign implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    @Column(name = "campaign_id", nullable = false)
    private String campaignId;

    @JsonIgnore
    @Column(name ="fundraiser_id", nullable = false)
    private String fundraiserId;

    @JsonIgnore
    @Column(name ="category_id", nullable = false)
    private String categoryId;

    @JsonProperty("title")
    @Column(name = "campaign_title", nullable = false)
    @NotNull
    @Size(min = 10, max = 256)
    private String campaignTitle;

    @JsonProperty("description")
    @Column(name = "campaign_description", nullable = false)
    @NotNull
    @Size(min = 10, max = 10240)
    private String campaignDescription;

    @JsonProperty("amount_goal")
    @Column(name = "amount_goal", nullable = false)
    @NotNull
    @Range(min = 0, max = 1000000)
    private Integer amountGoal;

    @JsonProperty("amount_raised")
    @Column(name = "amount_raised")
    private Integer amountRaised;

    @JsonProperty("expenses_description")
    @Column(name = "expenses_description", nullable = false)
    @NotNull
    @Size(min = 10, max = 10240)
    private String expensesDescription;

    @Column(name = "location", nullable = false)
    @NotNull
    @Size(min = 2, max = 256)
    private String location;

    @JsonProperty("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @NotNull
    private Date startDate;

    @JsonProperty("end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
    @Column(name = "end_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @NotNull
    private Date endDate;

    @Column(name = "backers")
    private int backers;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private CampaignStatus status;

    @Transient
    private Fundraiser fundraiser;

    @Transient
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

    public String getFundraiserId() {
        return fundraiserId;
    }

    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public Campaign withCategoryId(String categoryId) {
        this.setCategoryId(categoryId);
        return this;
    }

    public Campaign withFundraiserId(String fundraiserId) {
        this.setFundraiserId(fundraiserId);
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

    public Campaign withCategory(Category category){
        this.setCategory(category);
        return this;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaignId='" + campaignId + '\'' +
                ", fundraiserId='" + fundraiserId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", campaignTitle='" + campaignTitle + '\'' +
                ", campaignDescription='" + campaignDescription + '\'' +
                ", amountGoal=" + amountGoal +
                ", amountRaised=" + amountRaised +
                ", expensesDescription='" + expensesDescription + '\'' +
                ", location='" + location + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", backers=" + backers +
                ", status=" + status +
                ", fundraiser=" + fundraiser +
                ", category=" + category +
                ", resources=" + resources +
                '}';
    }
}
