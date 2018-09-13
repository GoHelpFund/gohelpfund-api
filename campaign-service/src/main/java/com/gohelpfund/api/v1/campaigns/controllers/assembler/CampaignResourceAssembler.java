package com.gohelpfund.api.v1.campaigns.controllers.assembler;

import com.gohelpfund.api.v1.campaigns.controllers.CampaignController;
import com.gohelpfund.api.v1.campaigns.model.Campaign;
import com.gohelpfund.api.v1.campaigns.model.status.CampaignStatusType;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CampaignResourceAssembler implements ResourceAssembler<Campaign, Resource<Campaign>> {

    @Override
    public Resource<Campaign> toResource(Campaign campaign) {

        // Unconditional links to single-item resource and aggregate root

        Resource<Campaign> campaignResource = new Resource<>(campaign,
                linkTo(methodOn(CampaignController.class).one(campaign.getCampaignId())).withSelfRel(),
                linkTo(methodOn(CampaignController.class).all()).withRel("categories")
        );

        /// Conditional links based on state of the campaign

        if (campaign.getStatus().getType() == CampaignStatusType.PENDING) {
            campaignResource.add(
                    linkTo(methodOn(CampaignController.class)
                            .cancel(campaign.getCampaignId())).withRel("cancel"));
            campaignResource.add(
                    linkTo(methodOn(CampaignController.class)
                            .complete(campaign.getCampaignId())).withRel("complete"));
        }


        return campaignResource;
    }
}