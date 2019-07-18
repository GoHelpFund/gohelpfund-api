package com.gohelpfund.api.v1.campaign_service.campaigns.model.expense;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.mediaresource.CampaignMediaResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "campaign_expenses")
@JsonPropertyOrder({"id", "amount", "description"})
public class CampaignExpense implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    @Column(name = "expense_id", nullable = false)
    private String expenseId;

    @JsonIgnore
    @Column(name = "campaign_id", nullable = false)
    private String campaignId;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "description", nullable = false)
    private String description;

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CampaignExpense withId(String id){
        this.setExpenseId(id);
        return this;
    }

    public CampaignExpense withCampaignId(String campaignId){
        this.setCampaignId(campaignId);
        return this;
    }

    @Override
    public String toString() {
        return "CampaignExpense{" +
                "expenseId='" + expenseId + '\'' +
                ", campaignId='" + campaignId + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
