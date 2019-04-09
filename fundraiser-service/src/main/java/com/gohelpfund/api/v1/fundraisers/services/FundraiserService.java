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
        fundraisers.forEach(fundraiser ->
                fundraiser.withStatus(status.getStatusByFundraiserId(fundraiser.getFundraiserId()))
                        .withSocial(social.getSocialByFundraiserId(fundraiser.getFundraiserId()))
                        .withProfessional(professional.getProfessionalByFundraiserId(fundraiser.getFundraiserId())));

        return fundraisers;
    }

    public Optional<Fundraiser> getOne(String fundraiserId) {
        Span newSpan = tracer.createSpan("getFundraiserDatabaseCall");
        logger.debug("In the fundraiserService.getOne() call");
        try {
            Optional<Fundraiser> fundraiser = repository.findByFundraiserId(fundraiserId);

            fundraiser.orElseThrow(() -> new EntityNotFoundException(Fundraiser.class, "id", fundraiserId));

            fundraiser.get().withStatus(status.getStatusByFundraiserId(fundraiser.get().getFundraiserId()))
                    .withSocial(social.getSocialByFundraiserId(fundraiser.get().getFundraiserId()))
                    .withProfessional(professional.getProfessionalByFundraiserId(fundraiser.get().getFundraiserId()));

            return fundraiser;
        } finally {
            newSpan.tag("peer.service", "postgres");
            newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
            tracer.close(newSpan);
        }
    }

    public Fundraiser save(Fundraiser fundraiser) {
        String id = UUID.randomUUID().toString();
        fundraiser.withId(id)
                .withStatus(status.saveStatus(id))
                .withSocial(social.saveSocial(fundraiser.getSocial().withFundraiserId(id)))
                .withProfessional(professional.saveProfessional(fundraiser.getProfessional().withFundraiserId(id)));

        Fundraiser newFundraiser = repository.save(fundraiser);

        simpleSourceBean.publishFundraiserChange("SAVE", newFundraiser.getFundraiserId());

        return newFundraiser;
    }

/*    public Fundraiser save() {
        Fundraiser newFundraiser = new Fundraiser();
        String id = UUID.randomUUID().toString();
        newFundraiser.withId(id)
                .withStatus(status.saveStatus(id))
                .withSocial(social.saveSocial(id))
                .withProfessional(professional.saveProfessional(id));

        Fundraiser savedFundraiser = repository.save(newFundraiser);

        simpleSourceBean.publishFundraiserChange("SAVE", savedFundraiser.getFundraiserId());

        return savedFundraiser;
    }*/

    public Fundraiser update(Fundraiser fundraiser) {
        Fundraiser newFundraiser = repository.save(fundraiser);

        simpleSourceBean.publishFundraiserChange("UPDATE", newFundraiser.getFundraiserId());

        return newFundraiser;
    }

    public void delete(String id) {
        repository.delete(id);

        simpleSourceBean.publishFundraiserChange("DELETE", id);
    }

    public void delete(Fundraiser fundraiser) {

        repository.delete(fundraiser.getFundraiserId());

        simpleSourceBean.publishFundraiserChange("DELETE", fundraiser.getFundraiserId());
    }

}
