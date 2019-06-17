package com.gohelpfund.api.v1.campaign_service.events.controllers.assembler;

import com.gohelpfund.api.v1.campaign_service.events.controllers.EventController;
import com.gohelpfund.api.v1.campaign_service.events.models.Event;
import com.gohelpfund.api.v1.campaign_service.events.models.EventDonateResponse;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EventDonateResponseAssembler implements ResourceAssembler<EventDonateResponse, Resource<EventDonateResponse>> {

    @Override
    public Resource<EventDonateResponse> toResource(EventDonateResponse donateResponse) {

        // Unconditional links to single-item resource and aggregate root

        Resource<EventDonateResponse> eventDonateResponse = new Resource<>(donateResponse,
                linkTo(methodOn(EventController.class).all()).withRel("events")
        );


        return eventDonateResponse;
    }
}