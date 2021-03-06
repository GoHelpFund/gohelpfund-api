package com.gohelpfund.api.v1.fundraiser_service.controllers.assembler;

import com.gohelpfund.api.v1.fundraiser_service.controllers.FundraiserController;
import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.Fundraiser;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class FundraiserResourceAssembler implements ResourceAssembler<Fundraiser, Resource<Fundraiser>> {

    @Override
    public Resource<Fundraiser> toResource(Fundraiser fundraiser) {

        // Unconditional links to single-item resource and aggregate root

        Resource<Fundraiser> fundraiserResource = new Resource<>(fundraiser,
                linkTo(methodOn(FundraiserController.class).one(fundraiser.getFundraiserId())).withSelfRel(),
                linkTo(methodOn(FundraiserController.class).all()).withRel("fundraisers")
        );

        /// Conditional links based on state of the fundraiser



        return fundraiserResource;
    }
}