package com.gohelpfund.api.v1.upload.controllers;

import com.gohelpfund.api.v1.security.controllers.exceptions.EntityNotFoundException;
import com.gohelpfund.api.v1.upload.controllers.assembler.UploadResourceAssembler;
import com.gohelpfund.api.v1.upload.model.Upload;
import com.gohelpfund.api.v1.upload.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/upload")
public class UploadController {
    @Autowired
    private UploadService service;

    @Autowired
    private UploadResourceAssembler assembler;

    @GetMapping()
    public Resource<Upload> one() {
        Upload upload = service.getUploadConfigParameters();

        if (upload == null) {

            throw new EntityNotFoundException(Upload.class);
        }

        return assembler.toResource(upload);
    }
}
