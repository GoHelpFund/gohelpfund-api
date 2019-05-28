package com.gohelpfund.api.v1.authentication_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonPropertyOrder({"id", "event_id", "fundraiser_id", "table_id"})
public class EventAttendance implements Serializable {

    @Id
    @JsonProperty("id")
    private String attendanceId;

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("fundraiser_id")
    private String fundraiserId;

    @JsonProperty("table_id")
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

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
