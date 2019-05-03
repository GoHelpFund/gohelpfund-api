package com.gohelpfund.api.v1.campaign_service.upload.controllers.exceptions;

public class UploadNotFoundException extends RuntimeException {

    public UploadNotFoundException(String id) {
        super("Could not find upload " + id);
    }
}