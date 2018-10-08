package com.gohelpfund.api.v1.fundraisers.controllers;

import com.gohelpfund.api.v1.fundraisers.controllers.assembler.FundraiserResourceAssembler;
import com.gohelpfund.api.v1.fundraisers.controllers.exceptions.FundraiserNotFoundException;
import com.gohelpfund.api.v1.fundraisers.model.Fundraiser;
import com.gohelpfund.api.v1.fundraisers.services.FundraiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("api/v1/fundraisers")
public class FundraiserController {

    @Autowired
    private FundraiserService service;

    @Autowired
    private FundraiserResourceAssembler assembler;


    @GetMapping()
    public Resources<Resource<Fundraiser>> all() {

        List<Resource<Fundraiser>> fundraisers = service.getAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(fundraisers,
                linkTo(methodOn(FundraiserController.class).all()).withSelfRel());
    }

    @GetMapping("/{fundraiserId}")
    public Resource<Fundraiser> one(@PathVariable("fundraiserId") String fundraiserId) {

        return assembler.toResource(
                service.getOne(fundraiserId)
                        .orElseThrow(() -> new FundraiserNotFoundException(fundraiserId)));
    }

    @PostMapping()
    public ResponseEntity<Resource<Fundraiser>> newCategory(@RequestBody Fundraiser fundraiser) {
        Fundraiser newFundraiser;
        if (fundraiser.getFundraiserId() == null) {
            newFundraiser = service.save();
        } else {
            newFundraiser = service.save(fundraiser);
        }
        return ResponseEntity
                .created(linkTo(methodOn(FundraiserController.class).one(newFundraiser.getFundraiserId())).toUri())
                .body(assembler.toResource(newFundraiser));
    }

    @DeleteMapping("/{fundraiserId}")
    public ResponseEntity<ResourceSupport> cancel(@PathVariable String fundraiserId) {
        Fundraiser fundraiser = service.getOne(fundraiserId).orElseThrow(() -> new FundraiserNotFoundException(fundraiserId));

        service.delete(fundraiser.getFundraiserId());

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PutMapping("/{fundraiserId}")
    public ResponseEntity<ResourceSupport> complete(@PathVariable String fundraiserId) {

        Fundraiser fundraiser = service.getOne(fundraiserId).orElseThrow(() -> new FundraiserNotFoundException(fundraiserId));

        service.save(fundraiser);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

}
