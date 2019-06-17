package com.gohelpfund.api.v1.campaign_service.events.controllers.assembler;

import com.gohelpfund.api.v1.campaign_service.campaigns.controllers.CampaignController;
import com.gohelpfund.api.v1.campaign_service.events.controllers.EventController;
import com.gohelpfund.api.v1.campaign_service.events.models.Event;
import com.gohelpfund.api.v1.campaign_service.events.models.EventAttendance;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EventAttendanceResourceAssembler implements ResourceAssembler<EventAttendance, Resource<EventAttendance>> {

    @Override
    public Resource<EventAttendance> toResource(EventAttendance attendance) {

        // Unconditional links to single-item resource and aggregate root

        Resource<EventAttendance> eventAttendanceResource = new Resource<>(attendance,
                linkTo(methodOn(EventController.class).one(attendance.getAttendanceId())).withSelfRel(),
                linkTo(methodOn(EventController.class).all()).withRel("events")
        );


        return eventAttendanceResource;
    }
}