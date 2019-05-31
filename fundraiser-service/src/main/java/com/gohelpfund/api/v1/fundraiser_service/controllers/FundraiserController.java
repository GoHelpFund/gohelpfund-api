package com.gohelpfund.api.v1.fundraiser_service.controllers;

import com.gohelpfund.api.v1.fundraiser_service.controllers.assembler.FundraiserResourceAssembler;
import com.gohelpfund.api.v1.fundraiser_service.controllers.exceptions.EntityNotFoundException;
import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.fundraiser_service.services.FundraiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
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

        Fundraiser fundraiser = service.getOne(fundraiserId);

        if (fundraiser == null) {
            throw new EntityNotFoundException(Fundraiser.class, "id", fundraiserId);
        }
        return assembler.toResource(fundraiser);

    }

    @PostMapping()
    public ResponseEntity<Resource<Fundraiser>> setFundraiser(@RequestBody Fundraiser fundraiser) {
        Fundraiser newFundraiser = service.save(fundraiser);
        return ResponseEntity
                .created(linkTo(methodOn(FundraiserController.class).one(newFundraiser.getFundraiserId())).toUri())
                .body(assembler.toResource(newFundraiser));
    }

    @PutMapping("/{fundraiserId}")
    public ResponseEntity<Resource<Fundraiser>> complete(@PathVariable String fundraiserId, @RequestBody Fundraiser updateFundraiser) {

        Fundraiser fundraiser = service.getOne(fundraiserId);

        if (fundraiser == null) {
            throw new EntityNotFoundException(Fundraiser.class, "id", fundraiserId);
        }

        // TODO: 15-Apr-19  implement update logic of a fundraiser
        Fundraiser newFundraiser = service.update(updateFundraiser);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(assembler.toResource(newFundraiser));
    }

/*    @DeleteMapping("/{fundraiserId}")
    public ResponseEntity<ResourceSupport> cancel(@PathVariable String fundraiserId) {
        Fundraiser fundraiser = service.getOne(fundraiserId);

        if(fundraiser == null){
            throw new EntityNotFoundException(Fundraiser.class, "id", fundraiserId);
        }

        service.delete(fundraiser.getFundraiserId());

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }*/

}
