package com.gohelpfund.api.v1.fundraisers.events.models;

public class FundraiserChangeModel {
    private String type;
    private String action;
    private String fundraiserId;
    private String correlationId;

    public FundraiserChangeModel() {
        super();
    }

    public FundraiserChangeModel(String type, String action, String organizationId, String correlationId) {
        super();
        this.type = type;
        this.action = action;
        this.fundraiserId = organizationId;
        this.correlationId = correlationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFundraiserId() {
        return fundraiserId;
    }

    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }


    @Override
    public String toString() {
        return "FundraiserChangeModel{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", fundraiserId='" + fundraiserId + '\'' +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}
