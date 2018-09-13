
package com.gohelpfund.api.v1.fundraisers.controllers.advices;

import com.gohelpfund.api.v1.fundraisers.controllers.exceptions.FundraiserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FundraiserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(FundraiserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String categoryNotFoundHandler(FundraiserNotFoundException ex) {
        return ex.getMessage();
    }
}