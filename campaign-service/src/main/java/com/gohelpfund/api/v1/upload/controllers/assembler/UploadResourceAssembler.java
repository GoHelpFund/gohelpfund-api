package com.gohelpfund.api.v1.upload.controllers.assembler;

import com.gohelpfund.api.v1.upload.controllers.UploadController;
import com.gohelpfund.api.v1.upload.model.Upload;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UploadResourceAssembler implements ResourceAssembler<Upload, Resource<Upload>> {

    @Override
    public Resource<Upload> toResource(Upload upload) {

        // Unconditional links to single-item resource and aggregate root

        Resource<Upload> uploadResource = new Resource<>(upload,
                ControllerLinkBuilder.linkTo(methodOn(UploadController.class).one()).withRel("com/gohelpfund/api/v1/upload")
        );

        /// Conditional links based on state of the upload


        return uploadResource;
    }
}