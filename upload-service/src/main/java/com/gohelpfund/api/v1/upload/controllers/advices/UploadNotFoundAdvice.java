
package com.gohelpfund.api.v1.upload.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.gohelpfund.api.v1.upload.controllers.exceptions.UploadNotFoundException;

@ControllerAdvice
public class UploadNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UploadNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String uploadNotFoundHandler(UploadNotFoundException ex) {
        return ex.getMessage();
    }
}