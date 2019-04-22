package com.gohelpfund.api.v1.campaigns.controllers;

import com.gohelpfund.api.v1.campaigns.controllers.assembler.CampaignResourceAssembler;
import com.gohelpfund.api.v1.campaigns.model.Campaign;
import com.gohelpfund.api.v1.campaigns.model.status.CampaignStatusType;
import com.gohelpfund.api.v1.campaigns.services.CampaignService;
import com.gohelpfund.api.v1.config.ServiceConfig;
import com.gohelpfund.api.v1.security.controllers.exceptions.EntityNotFoundException;
import com.gohelpfund.api.v1.utils.UserContextHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/campaigns")
public class CampaignController {
    private static final Logger logger = LoggerFactory.getLogger(CampaignController.class);

    private final CampaignService service;
    private final CampaignResourceAssembler assembler;
    private final ServiceConfig config;

    @Autowired
    CampaignController(CampaignService campaignService,
                       CampaignResourceAssembler assembler,
                       ServiceConfig config) {
        this.service = campaignService;
        this.assembler = assembler;
        this.config = config;
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
        Campaign campaign = service.getCampaignById(campaignId);

        if (campaign == null) {
            throw new EntityNotFoundException(Campaign.class, "id", campaignId);
        }

        return assembler.toResource(campaign);
    }

    @PostMapping()
    public ResponseEntity<Resource<Campaign>> newCampaign(@RequestBody @Valid Campaign campaign) {
        campaign.setFundraiserId(getValueFromJWTByKey("fundraiser_id"));

        Campaign newCampaign = service.save(campaign);

        return ResponseEntity
                .created(linkTo(methodOn(CampaignController.class).one(newCampaign.getCampaignId())).toUri())
                .body(assembler.toResource(newCampaign));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<ResourceSupport> complete(@PathVariable("id") String campaignId) {

        Campaign campaign = service.getCampaignById(campaignId);

        if (campaign == null) {
            throw new EntityNotFoundException(Campaign.class, "id", campaignId);
        }

        if (campaign.getStatus().getType() == CampaignStatusType.PENDING) {
            campaign.getStatus().setType(CampaignStatusType.COMPLETED);
            Campaign newCampaign = service.updateCampaign(campaign);

            return ResponseEntity.ok(assembler.toResource(newCampaign));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't complete a campaign that is in the " + campaign.getStatus() + " status"));
    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<ResourceSupport> cancel(@PathVariable("id") String campaignId) {
        Campaign campaign = service.getCampaignById(campaignId);

        if (campaign == null) {
            throw new EntityNotFoundException(Campaign.class, "id", campaignId);
        }

        if (campaign.getStatus().getType() == CampaignStatusType.PENDING) {
            campaign.getStatus().setType(CampaignStatusType.CANCELED);
            Campaign newCampaign = service.updateCampaign(campaign);

            return ResponseEntity.ok(assembler.toResource(newCampaign));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't cancel a campaign that is in the " + campaign.getStatus() + " status"));
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
