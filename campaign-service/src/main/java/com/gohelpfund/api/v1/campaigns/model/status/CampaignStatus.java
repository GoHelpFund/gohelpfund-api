package com.gohelpfund.api.v1.campaigns.model.status;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity
@Table(name = "campaign_statuses")
@JsonPropertyOrder({"type", "typeDescription", "subType", "subTypeDescription"})
public class CampaignStatus {

    @Id
    @JsonIgnore
    @Column(name = "status_id", nullable = false)
    private String statusId;

    @JsonIgnore
    @Column(name = "campaign_id", nullable = false)
    private String campaignId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CampaignStatusType type;

    @JsonProperty("type_description")
    @Column(name = "type_description", nullable = false)
    private String typeDescription;

    @JsonProperty("sub_type")
    @Enumerated(EnumType.STRING)
    @Column(name = "sub_type", nullable = false)
    private CampaignStatusSubType subType;

    @JsonProperty("sub_type_description")
    @Column(name = "sub_type_description", nullable = false)
    private String subTypeDescription;

    public CampaignStatus(){
        this.type = CampaignStatusType.PENDING;
        this.typeDescription = "Campaign is not public";
        this.subType = CampaignStatusSubType.REGISTRATION_REQUIRED;
        this.subTypeDescription = "Campaign created as an guest - user needs to register";
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public CampaignStatusType getType() {
        return type;
    }

    public void setType(CampaignStatusType type) {
        this.type = type;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public CampaignStatusSubType getSubType() {
        return subType;
    }

    public void setSubType(CampaignStatusSubType subType) {
        this.subType = subType;
    }

    public String getSubTypeDescription() {
        return subTypeDescription;
    }

    public void setSubTypeDescription(String subTypeDescription) {
        this.subTypeDescription = subTypeDescription;
    }

    public CampaignStatus withId(String id){
        this.setStatusId(id);
        return this;
    }

    public CampaignStatus withCampaignId(String campaignId){
        this.setCampaignId(campaignId);
        return this;
    }
}
