package com.gohelpfund.api.v1.campaign_service.events.controllers;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.donation.Donation;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.Wallet;
import com.gohelpfund.api.v1.campaign_service.campaigns.model.wallet.promise.PromiseWalletBacker;
import com.gohelpfund.api.v1.campaign_service.config.ServiceConfig;
import com.gohelpfund.api.v1.campaign_service.events.controllers.assembler.EventAttendanceResourceAssembler;
import com.gohelpfund.api.v1.campaign_service.events.controllers.assembler.EventDonateResponseAssembler;
import com.gohelpfund.api.v1.campaign_service.events.controllers.assembler.EventResourceAssembler;
import com.gohelpfund.api.v1.campaign_service.events.models.Event;
import com.gohelpfund.api.v1.campaign_service.events.models.EventAttendance;
import com.gohelpfund.api.v1.campaign_service.events.models.EventDonateResponse;
import com.gohelpfund.api.v1.campaign_service.events.services.EventAttendanceService;
import com.gohelpfund.api.v1.campaign_service.events.services.EventService;
import com.gohelpfund.api.v1.campaign_service.security.controllers.exceptions.EntityNotFoundException;
import com.gohelpfund.api.v1.campaign_service.utils.UserContextHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/events")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final EventResourceAssembler assembler;
    private final EventAttendanceResourceAssembler attendanceAssembler;
    private final EventDonateResponseAssembler donateResponseAssembler;
    private final EventService service;
    private final EventAttendanceService attendanceService;
    private final ServiceConfig config;

    @Autowired
    EventController(EventService eventService,
                    EventAttendanceService attendanceService,
                    EventResourceAssembler assembler,
                    EventAttendanceResourceAssembler attendanceAssembler,
                    EventDonateResponseAssembler donateResponseAssembler,
                    ServiceConfig config) {
        this.service = eventService;
        this.attendanceService = attendanceService;
        this.assembler = assembler;
        this.attendanceAssembler = attendanceAssembler;
        this.donateResponseAssembler = donateResponseAssembler;
        this.config = config;
    }

    @GetMapping()
    public Resources<Resource<Event>> all() {

        List<Resource<Event>> events = service.getEvents().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(events,
                linkTo(methodOn(EventController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public Resource<Event> one(@PathVariable("id") String eventId) {
        Event event = service.getEventById(eventId);

        if (event == null) {
            throw new EntityNotFoundException(Event.class, "id", eventId);
        }

        return assembler.toResource(event);
    }

    @GetMapping("/{id}/attendance")
    public Resources<Resource<EventAttendance>> getAttendance(@PathVariable("id") String eventId) {

        List<Resource<EventAttendance>> attendances = attendanceService.getEventAttendance(eventId).stream()
                .map(attendanceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(attendances,
                linkTo(methodOn(EventController.class).all()).withSelfRel());
    }

    @PostMapping("/{id}/attendance")
    public ResponseEntity<ResourceSupport> setAttendance(@PathVariable("id") String id, @RequestBody EventAttendance attendance) {
        // TODO: 28-may-19  implement exists logic of a event
        EventAttendance newAttendance = attendanceService.save(id, attendance);
        return ResponseEntity.ok(attendanceAssembler.toResource(newAttendance));
    }

    @PostMapping("/{id}/donate")
    public ResponseEntity<ResourceSupport> offlineDonate(@PathVariable("id") String eventId, @RequestBody @Valid Donation donation) {
        return donate(eventId, donation);
    }

    @PostMapping("/{id}/auctionDonate")
    public ResponseEntity<ResourceSupport> auctionDonate(@PathVariable("id") String eventId, @RequestBody @Valid Donation donation) {
        return donate(eventId, donation);
    }

    private ResponseEntity<ResourceSupport> donate(String eventId, Donation donation) {
        Event event = service.getEventById(eventId);

        if (event == null) {
            throw new EntityNotFoundException(Event.class, "id", eventId);
        }

        String walletId = event.getWalletId();
        String fundraiserId = getValueFromJWTByKey("fundraiser_id");
        Integer amount = donation.getAmount();

        Wallet newWallet = service.updateEvent(eventId, walletId, fundraiserId, amount);
        event.withWallet(newWallet);

        PromiseWalletBacker backer = newWallet.getPromiseWallet().getBackers().stream()
                .filter(b -> b.getFundraiser_id().equals(fundraiserId))
                .findAny()
                .orElse(null);

        EventDonateResponse donateResponse = new EventDonateResponse();

        if(backer != null) {
            donateResponse.setTotalAmountDonated(backer.getTotalAmount());
        }

        return ResponseEntity.ok(donateResponseAssembler.toResource(donateResponse));
    }

    private String getValueFromJWTByKey(String key) {
        String value;
        String authToken = UserContextHolder.getContext().getAuthToken().replace("Bearer ", "");

        try {
            Claims claims =
                    Jwts.parser()
                            .setSigningKey(config.getJwtSigningKey().getBytes("UTF-8"))
                            .parseClaimsJws(authToken)
                            .getBody();
            value = claims.get(key).toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Unable to parse JWT");
        }
        return value;
    }
}
