package com.gohelpfund.api.v1.authentication_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonPropertyOrder({"fundraiser_id", "fundraiser_name", "table_id"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventAttendance implements Serializable {

    @JsonProperty("fundraiser_name")
    private String fundraiserName;

    @JsonProperty("fundraiser_id")
    private String fundraiserId;

    @JsonProperty("table_id")
    private String tableId;

    public String getFundraiserId() {
        return fundraiserId;
    }

    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    public String getFundraiserName() {
        return fundraiserName;
    }

    public void setFundraiserName(String fundraiserName) {
        this.fundraiserName = fundraiserName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "EventAttendance{" +
                "fundraiserName='" + fundraiserName + '\'' +
                ", fundraiserId='" + fundraiserId + '\'' +
                ", tableId='" + tableId + '\'' +
                '}';
    }
}
