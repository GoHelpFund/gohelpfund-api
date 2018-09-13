package com.gohelpfund.api.v1.fundraisers.services;

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

        return repository.save(fundraiser);
    }

    public Fundraiser save() {
        Fundraiser newFundraiser = new Fundraiser();
        String id = UUID.randomUUID().toString();
        newFundraiser.withId(id)
                .withStatus(status.saveStatus(id))
                .withSocial(social.saveSocial(id))
                .withProfessional(professional.saveProfessional(id));

        return repository.save(newFundraiser);
    }

    public Fundraiser update(Fundraiser fundraiser) {
        return repository.save(fundraiser);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public void delete(Fundraiser fundraiser) {
        repository.delete(fundraiser.getFundraiserId());
    }

}
