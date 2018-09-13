package com.gohelpfund.api.v1.fundraisers.services;

import com.gohelpfund.api.v1.fundraisers.model.FundraiserProfessional;
import com.gohelpfund.api.v1.fundraisers.repository.FundraiserProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FundraiserProfessionalService {

    @Autowired
    private FundraiserProfessionalRepository repository;


    public List<FundraiserProfessional> getProfessionals() {
        List<FundraiserProfessional> professionals = repository.findAll();

        return professionals;
    }

    public FundraiserProfessional getProfessionalByFundraiserId(String fundraiserId) {
        return repository.findByFundraiserId(fundraiserId);
    }

    public FundraiserProfessional getProfessionalById(String professionalId) {
        return repository.findByProfessionalId(professionalId);
    }

    public FundraiserProfessional saveProfessional(FundraiserProfessional professional) {
        professional.withId(UUID.randomUUID().toString());
        return repository.save(professional);
    }

    public FundraiserProfessional saveProfessional() {
        return repository.save(new FundraiserProfessional().withId(UUID.randomUUID().toString()));
    }

    public FundraiserProfessional saveProfessional(String fundraiserId) {
        return repository.save(new FundraiserProfessional().withId(UUID.randomUUID().toString())
                .withFundraiserId(fundraiserId));
    }

    public FundraiserProfessional updateProfessional(FundraiserProfessional professional) {
        return repository.save(professional);
    }

    public void deleteProfessional(FundraiserProfessional professional) {
        repository.delete(professional.getFundraiserId());
    }

}
