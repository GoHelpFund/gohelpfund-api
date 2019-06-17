package com.gohelpfund.api.v1.campaign_service.events.controllers.assembler;

import com.gohelpfund.api.v1.campaign_service.campaigns.controllers.CampaignController;
import com.gohelpfund.api.v1.campaign_service.events.controllers.EventController;
import com.gohelpfund.api.v1.campaign_service.events.models.Event;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EventResourceAssembler implements ResourceAssembler<Event, Resource<Event>> {

    @Override
    public Resource<Event> toResource(Event event) {

        // Unconditional links to single-item resource and aggregate root

        Resource<Event> eventResource = new Resource<>(event,
                linkTo(methodOn(EventController.class).one(event.getEventId())).withSelfRel(),
                linkTo(methodOn(EventController.class).all()).withRel("events")
        );


        return eventResource;
    }
}