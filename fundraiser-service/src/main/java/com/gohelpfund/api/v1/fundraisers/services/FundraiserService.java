package com.gohelpfund.api.v1.fundraisers.services;

import com.gohelpfund.api.v1.fundraisers.events.source.SimpleSourceBean;
import com.gohelpfund.api.v1.fundraisers.model.Fundraiser;
import com.gohelpfund.api.v1.fundraisers.repository.FundraiserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FundraiserService {

    @Autowired
    private FundraiserRepository repository;

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
        Optional<Fundraiser> fundraiser =  repository.findByFundraiserId(fundraiserId);

        fundraiser.get().withStatus(status.getStatusByFundraiserId(fundraiser.get().getFundraiserId()))
                .withSocial(social.getSocialByFundraiserId(fundraiser.get().getFundraiserId()))
                .withProfessional(professional.getProfessionalByFundraiserId(fundraiser.get().getFundraiserId()));

        return fundraiser;
    }

    public Fundraiser save(Fundraiser fundraiser) {
        String id = UUID.randomUUID().toString();
        fundraiser.withId(id)
                .withStatus(status.saveStatus(id))
                .withSocial(social.saveSocial(fundraiser.getSocial().withFundraiserId(id)))
                .withProfessional(professional.saveProfessional(fundraiser.getProfessional().withFundraiserId(id)));

        Fundraiser newFundraiser =  repository.save(fundraiser);

        simpleSourceBean.publishOrgChange("SAVE", newFundraiser.getFundraiserId());

        return newFundraiser;
    }

    public Fundraiser save() {
        Fundraiser newFundraiser = new Fundraiser();
        String id = UUID.randomUUID().toString();
        newFundraiser.withId(id)
                .withStatus(status.saveStatus(id))
                .withSocial(social.saveSocial(id))
                .withProfessional(professional.saveProfessional(id));

        Fundraiser savedFundraiser =  repository.save(newFundraiser);

        simpleSourceBean.publishOrgChange("SAVE", savedFundraiser.getFundraiserId());

        return savedFundraiser;
    }

    public Fundraiser update(Fundraiser fundraiser) {
        Fundraiser newFundraiser =  repository.save(fundraiser);

        simpleSourceBean.publishOrgChange("UPDATE", newFundraiser.getFundraiserId());

        return newFundraiser;
    }

    public void delete(String id) {
        repository.delete(id);

        simpleSourceBean.publishOrgChange("DELETE", id);
    }

    public void delete(Fundraiser fundraiser) {

        repository.delete(fundraiser.getFundraiserId());

        simpleSourceBean.publishOrgChange("DELETE", fundraiser.getFundraiserId());
    }

}
