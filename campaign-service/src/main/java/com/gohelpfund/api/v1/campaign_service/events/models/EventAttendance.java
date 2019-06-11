package com.gohelpfund.api.v1.campaign_service.events.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "event_attendance")
@JsonPropertyOrder({"fundraiser_id", "fundraiser_name", "table_id"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventAttendance implements Serializable {

    @Id
    @JsonIgnore
    //@JsonProperty("id")
    @Column(name = "attendance_id", nullable = false)
    private String attendanceId;

    @JsonIgnore
    @Column(name = "event_id", nullable = false)
    private String eventId;

    @NotNull
    @JsonProperty("fundraiser_id")
    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiserId;

    @NotNull
    @JsonProperty("fundraiser_name")
    @Column(name = "fundraiser_name", nullable = false)
    private String fundraiserName;

    @NotNull
    @JsonProperty("fundraiser_type")
    @Column(name = "fundraiser_type", nullable = false)
    private String fundraiserType;

    @NotNull
    @JsonProperty("table_id")
    @Column(name = "table_id", nullable = false)
    private String tableId;


    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

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

    public String getFundraiserType() {
        return fundraiserType;
    }

    public void setFundraiserType(String fundraiserType) {
        this.fundraiserType = fundraiserType;
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
                "attendanceId='" + attendanceId + '\'' +
                ", eventId='" + eventId + '\'' +
                ", fundraiserId='" + fundraiserId + '\'' +
                ", fundraiserName='" + fundraiserName + '\'' +
                ", fundraiserType='" + fundraiserType + '\'' +
                ", tableId='" + tableId + '\'' +
                '}';
    }
}
