package com.gohelpfund.api.v1.campaigns.model.mediaresource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gohelpfund.api.v1.campaigns.model.mediaresource.status.CampaignMediaResourceStatus;

import javax.persistence.*;

@Entity
@Table(name = "campaign_media_resources")
@JsonPropertyOrder({"id", "name", "url", "type", "format", "status"})
public class CampaignMediaResource {
    @Id
    @JsonProperty("id")
    @Column(name = "resource_id", nullable = false)
    private String resourceId;

    @JsonIgnore
    @Column(name = "campaign_id", nullable = false)
    private String campaignId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "format", nullable = false)
    private String format;

    @Column(name = "url", nullable = false)
    private String url;

    @JsonIgnore
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CampaignMediaResourceStatus status;

    public String getResourceId() {
        return resourceId;
    }

    public void setId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CampaignMediaResourceStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignMediaResourceStatus status) {
        this.status = status;
    }

    public CampaignMediaResource withId(String id){
        this.setId(id);
        return this;
    }

    public CampaignMediaResource withCampaignId(String campaignId){
        this.setCampaignId(campaignId);
        return this;
    }
}
