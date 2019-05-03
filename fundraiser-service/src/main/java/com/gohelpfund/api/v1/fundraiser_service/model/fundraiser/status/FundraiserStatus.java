package com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.status;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fundraiser_statuses")
@JsonPropertyOrder({"type", "typeDescription", "subType", "subTypeDescription"})
public class FundraiserStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "status_id", nullable = false)
    private String statusId;

    @JsonIgnore
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FundraiserStatusType type;

    @JsonProperty("type_description")
    @Column(name = "type_description", nullable = false)
    private String typeDescription;

    @JsonProperty("sub_type")
    @Enumerated(EnumType.STRING)
    @Column(name = "sub_type", nullable = false)
    private FundraiserStatusSubType subType;

    @JsonProperty("sub_type_description")
    @Column(name = "sub_type_description", nullable = false)
    private String subTypeDescription;


    public FundraiserStatus(){
        this.type = FundraiserStatusType.PENDING;
        this.typeDescription = "User is pending";
        this.subType = FundraiserStatusSubType.VERIFICATION_REQUIRED;
        this.subTypeDescription = "User needs to verify his account";
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getFundraiserId() {
        return fundraiserId;
    }

    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    public FundraiserStatusType getType() {
        return type;
    }

    public void setType(FundraiserStatusType type) {
        this.type = type;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public FundraiserStatusSubType getSubType() {
        return subType;
    }

    public void setSubType(FundraiserStatusSubType subType) {
        this.subType = subType;
    }

    public String getSubTypeDescription() {
        return subTypeDescription;
    }

    public void setSubTypeDescription(String subTypeDescription) {
        this.subTypeDescription = subTypeDescription;
    }

    public FundraiserStatus withId(String id) {
        this.setStatusId(id);
        return this;
    }

    public FundraiserStatus withFundraiserId(String fundraiserId) {
        this.setFundraiserId(fundraiserId);
        return this;
    }

    @Override
    public String toString() {
        return "FundraiserStatus{" +
                "statusId='" + statusId + '\'' +
                ", fundraiserId='" + fundraiserId + '\'' +
                ", type=" + type +
                ", typeDescription='" + typeDescription + '\'' +
                ", subType=" + subType +
                ", subTypeDescription='" + subTypeDescription + '\'' +
                '}';
    }
}
