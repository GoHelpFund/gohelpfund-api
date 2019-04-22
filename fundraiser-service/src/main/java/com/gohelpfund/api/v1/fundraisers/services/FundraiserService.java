package com.gohelpfund.api.v1.fundraisers.services;

import com.gohelpfund.api.v1.fundraisers.controllers.exceptions.EntityNotFoundException;
import com.gohelpfund.api.v1.fundraisers.events.source.SimpleSourceBean;
import com.gohelpfund.api.v1.fundraisers.model.Fundraiser;
import com.gohelpfund.api.v1.fundraisers.repository.FundraiserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FundraiserService {
    private static final Logger logger = LoggerFactory.getLogger(FundraiserService.class);

    @Autowired
    private FundraiserRepository repository;

    @Autowired
    private Tracer tracer;

    @Autowired
    private FundraiserSocialService social;

    @Autowired
    private FundraiserProfessionalService professional;

    @Autowired
    private FundraiserStatusService status;

    @Autowired
    SimpleSourceBean simpleSourceBean;

    public List<Fundraiser> getAll() {
        List<Fundraiser> fundraisers = repository.findAll();
        if (fundraisers == null) {
            logger.debug("GET | PostgreSQL | empty | fundraisers size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | fundraisers size: {}", fundraisers.size());
            fundraisers.forEach(fundraiser ->
                    fundraiser.withStatus(status.getStatusByFundraiserId(fundraiser.getFundraiserId()))
                            .withSocial(social.getSocialByFundraiserId(fundraiser.getFundraiserId()))
                            .withProfessional(professional.getProfessionalByFundraiserId(fundraiser.getFundraiserId())));
        }
        return fundraisers;
    }

    public Fundraiser getOne(String fundraiserId) {
        Span newSpan = tracer.createSpan("getFundraiserDatabaseCall");
        try {
            Fundraiser fundraiser = repository.findByFundraiserId(fundraiserId);

            if (fundraiser == null) {
                logger.debug("GET | PostgreSQL | not found | fundraiser id: {}", fundraiserId);
            } else {
                logger.debug("GET | PostgreSQL | found | fundraiser id: {}", fundraiserId);
                fundraiser
                        .withStatus(status.getStatusByFundraiserId(fundraiser.getFundraiserId()))
                        .withSocial(social.getSocialByFundraiserId(fundraiser.getFundraiserId()))
                        .withProfessional(professional.getProfessionalByFundraiserId(fundraiser.getFundraiserId()));

            }

            return fundraiser;
        } finally {
            newSpan.tag("peer.service", "postgres");
            newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
            tracer.close(newSpan);
        }
    }

    public Fundraiser save() {
        String id = UUID.randomUUID().toString();

        Fundraiser fundraiser = new Fundraiser()
                .withId(id)
                .withStatus(status.saveStatus(id))
                .withSocial(social.saveSocial(id))
                .withProfessional(professional.saveProfessional(id));

        Fundraiser newFundraiser = repository.save(fundraiser);
        logger.debug("POST | PostgreSQL | created | fundraiser id: {} ", newFundraiser.getFundraiserId());

        simpleSourceBean.publishFundraiserChange("SAVE", newFundraiser.getFundraiserId());

        return newFundraiser;
    }

    public Fundraiser update(Fundraiser fundraiser) {
        Fundraiser newFundraiser = repository.save(fundraiser);

        simpleSourceBean.publishFundraiserChange("UPDATE", newFundraiser.getFundraiserId());
        logger.debug("PUT | PostgreSQL | updated | fundraiser id: {} ", newFundraiser.getFundraiserId());

        return newFundraiser;
    }

    private void delete(String id) {
        repository.delete(id);
        logger.debug("DELETE | PostgreSQL | removed | fundraiser id: {} ", id);

        simpleSourceBean.publishFundraiserChange("DELETE", id);
    }

}
