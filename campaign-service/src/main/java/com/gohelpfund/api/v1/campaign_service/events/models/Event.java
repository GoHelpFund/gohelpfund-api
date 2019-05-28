package com.gohelpfund.api.v1.campaign_service.events.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.Wallet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@JsonPropertyOrder({"id", "title", "description", "location",
        "start_date", "wallet", "attendance", "fundraiser"})
public class Event implements Serializable {

    @Id
    @JsonProperty("id")
    @Column(name = "event_id", nullable = false)
    private String eventId;

    @JsonIgnore
    @Column(name ="fundraiser_id", nullable = false)
    private String fundraiserId;

    @JsonIgnore
    @Column(name ="wallet_id", nullable = false)
    private String walletId;

    @JsonProperty("title")
    @Column(name = "event_title", nullable = false)
    @NotNull
    @Size(min = 1, max = 256)
    private String eventTitle;

    @JsonProperty("description")
    @Column(name = "event_description", nullable = false)
    @NotNull
    @Size(min = 1, max = 10240)
    private String eventDescription;

    @Column(name = "location", nullable = false)
    @NotNull
    @Size(min = 1, max = 256)
    private String location;

    @JsonProperty("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @NotNull
    private Date startDate;

    @Transient
    private Wallet wallet;

    @Transient
    private List<EventAttendance> attendance;

    @Transient
    private Fundraiser fundraiser;

    public Event(){

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

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
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

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public List<EventAttendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<EventAttendance> attendance) {
        this.attendance = attendance;
    }

    public Fundraiser getFundraiser() {
        return fundraiser;
    }

    public void setFundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
    }

    public Event withFundraiser(Fundraiser fundraiser){
        this.setFundraiser(fundraiser);
        return this;
    }

    public Event withWallet(Wallet wallet){
        this.setWallet(wallet);
        return this;
    }

    public Event withAttendance(List<EventAttendance> attendance){
        this.setAttendance(attendance);
        return this;
    }
}

