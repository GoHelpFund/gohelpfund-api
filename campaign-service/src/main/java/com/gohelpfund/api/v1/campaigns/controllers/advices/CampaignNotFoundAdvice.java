
package com.gohelpfund.api.v1.campaigns.controllers.advices;

import com.gohelpfund.api.v1.campaigns.controllers.exceptions.CampaignNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CampaignNotFoundAdvice extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CampaignNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String campaignNotFoundHandler(CampaignNotFoundException ex) {
        return ex.getMessage();
    }

}