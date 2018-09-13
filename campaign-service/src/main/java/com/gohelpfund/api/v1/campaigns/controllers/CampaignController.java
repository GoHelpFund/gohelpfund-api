package com.gohelpfund.api.v1.campaigns.controllers;

import com.gohelpfund.api.v1.campaigns.controllers.assembler.CampaignResourceAssembler;
import com.gohelpfund.api.v1.campaigns.controllers.exceptions.CampaignNotFoundException;
import com.gohelpfund.api.v1.campaigns.model.Campaign;
import com.gohelpfund.api.v1.campaigns.model.status.CampaignStatusType;
import com.gohelpfund.api.v1.campaigns.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "v1/campaigns")
public class CampaignController {

    private final CampaignService service;
    private final CampaignResourceAssembler assembler;

    @Autowired
    CampaignController(CampaignService campaignService,
                       CampaignResourceAssembler assembler) {
        this.service = campaignService;
        this.assembler = assembler;
    }

    @GetMapping()
    public Resources<Resource<Campaign>> all() {

        List<Resource<Campaign>> campaigns = service.getCampaigns().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(campaigns,
                linkTo(methodOn(CampaignController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public Resource<Campaign> one(@PathVariable("id") String campaignId) {

        return assembler.toResource(
                service.getCampaignById(campaignId)
                        .orElseThrow(() -> new CampaignNotFoundException(campaignId)));
    }

    @PostMapping()
    public ResponseEntity<Resource<Campaign>> newCategory(@RequestBody Campaign campaign) {
        Campaign newCampaign = service.save(campaign);

        return ResponseEntity
                .created(linkTo(methodOn(CampaignController.class).one(newCampaign.getCampaignId())).toUri())
                .body(assembler.toResource(newCampaign));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<ResourceSupport> complete(@PathVariable("id") String campaignId) {

        Campaign campaign = service.getCampaignById(campaignId).orElseThrow(() -> new CampaignNotFoundException(campaignId));

        if (campaign.getStatus().getType() == CampaignStatusType.PENDING) {
            campaign.getStatus().setType(CampaignStatusType.COMPLETED);
            return ResponseEntity.ok(assembler.toResource(service.updateCampaign(campaign)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't complete an campaign that is in the " + campaign.getStatus() + " status"));
    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<ResourceSupport> cancel(@PathVariable("id") String campaignId) {
        Campaign campaign = service.getCampaignById(campaignId).orElseThrow(() -> new CampaignNotFoundException(campaignId));

        if (campaign.getStatus().getType() == CampaignStatusType.PENDING) {
            campaign.getStatus().setType(CampaignStatusType.CANCELED);
            return ResponseEntity.ok(assembler.toResource(service.updateCampaign(campaign)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't cancel an campaign that is in the " + campaign.getStatus() + " status"));
    }

}
