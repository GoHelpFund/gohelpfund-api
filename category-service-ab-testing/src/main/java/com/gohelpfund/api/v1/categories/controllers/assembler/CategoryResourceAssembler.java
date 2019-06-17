package com.gohelpfund.api.v1.wallets.controllers.assembler;

import com.gohelpfund.api.v1.wallets.controllers.CategoryController;
import com.gohelpfund.api.v1.wallets.model.Category;
import com.gohelpfund.api.v1.wallets.model.CategoryStatus;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CategoryResourceAssembler implements ResourceAssembler<Category, Resource<Category>> {

    @Override
    public Resource<Category> toResource(Category category) {

        // Unconditional links to single-item resource and aggregate root

        Resource<Category> categoryResource = new Resource<>(category,
                linkTo(methodOn(CategoryController.class).one(category.getCategoryId())).withSelfRel(),
                linkTo(methodOn(CategoryController.class).all()).withRel("wallets")
        );

        /// Conditional links based on state of the category

        if (category.getStatus() == CategoryStatus.PENDING) {
            categoryResource.add(
                    linkTo(methodOn(CategoryController.class)
                            .cancel(category.getCategoryId())).withRel("cancel"));
            categoryResource.add(
                    linkTo(methodOn(CategoryController.class)
                            .complete(category.getCategoryId())).withRel("complete"));
        }


        return categoryResource;
    }
}