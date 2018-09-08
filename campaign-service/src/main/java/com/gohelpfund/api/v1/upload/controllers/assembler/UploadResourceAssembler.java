package com.gohelpfund.api.v1.upload.controllers.assembler;

import com.gohelpfund.api.v1.categories.controllers.CategoryController;
import com.gohelpfund.api.v1.categories.model.Category;
import com.gohelpfund.api.v1.categories.model.CategoryStatus;
import com.gohelpfund.api.v1.upload.controllers.UploadController;
import com.gohelpfund.api.v1.upload.model.Upload;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UploadResourceAssembler implements ResourceAssembler<Upload, Resource<Upload>> {

    @Override
    public Resource<Upload> toResource(Upload upload) {

        // Unconditional links to single-item resource and aggregate root

        Resource<Upload> categoryResource = new Resource<>(upload,
                linkTo(methodOn(UploadController.class).one()).withRel("upload")
        );

        /// Conditional links based on state of the upload


        return categoryResource;
    }
}