package com.gohelpfund.api.v1.fundraiser_service.services;

import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.professional.FundraiserProfessional;
import com.gohelpfund.api.v1.fundraiser_service.repository.FundraiserProfessionalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FundraiserProfessionalService {
    private static final Logger logger = LoggerFactory.getLogger(FundraiserProfessionalService.class);

    @Autowired
    private FundraiserProfessionalRepository repository;

    public List<FundraiserProfessional> getProfessionals() {
        List<FundraiserProfessional> professionals = repository.findAll();

        if (professionals == null) {
            logger.debug("GET | PostgreSQL | empty | fundraisers_professional size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | fundraisers_professional size: {}", professionals.size());
        }
        return professionals;
    }

    public FundraiserProfessional getProfessionalByFundraiserId(String fundraiserId) {
        FundraiserProfessional professional = repository.findByFundraiserId(fundraiserId);

        if (professional == null) {
            logger.debug("GET | PostgreSQL | not found | fundraiser_professional by fundraiser id: {}", fundraiserId);
        } else {
            logger.debug("GET | PostgreSQL | found | fundraiser_professional id: {} fundraiser id: {}", professional.getProfessionalId(), fundraiserId);
        }
        return professional;
    }

    public FundraiserProfessional getProfessionalById(String professionalId) {
        FundraiserProfessional professional = repository.findByProfessionalId(professionalId);

        if (professional == null) {
            logger.debug("GET | PostgreSQL | not found | fundraiser_professional id: {}", professionalId);
        } else {
            logger.debug("GET | PostgreSQL | found | fundraiser id: {} fundraiser_professional id: {}", professional.getFundraiserId(), professionalId);
        }
        return professional;
    }

    public FundraiserProfessional saveProfessional(FundraiserProfessional professional) {
        professional
                .withId(UUID.randomUUID().toString());

        FundraiserProfessional newProfessional =  repository.save(professional);
        logger.debug("POST | PostgreSQL | created | fundraiser_professional id: {} ", newProfessional.getProfessionalId());

        return newProfessional;
    }

    public FundraiserProfessional saveProfessional(String fundraiserId) {
        FundraiserProfessional professional = new FundraiserProfessional()
                .withId(UUID.randomUUID().toString())
                .withFundraiserId(fundraiserId);

        FundraiserProfessional newProfessional = repository.save(professional);
        logger.debug("POST | PostgreSQL | created | fundraiser_professional id: {} fundraiser id: {} ", newProfessional.getProfessionalId(), fundraiserId);

        return newProfessional;
    }

    public FundraiserProfessional updateProfessional(FundraiserProfessional professional) {
        FundraiserProfessional newProfessional =  repository.save(professional);
        logger.debug("PUT | PostgreSQL | updated | fundraiser_professional id: {} fundraiser id: {} ", newProfessional.getProfessionalId(), newProfessional.getFundraiserId());

        return newProfessional;
    }

    public void deleteProfessional(FundraiserProfessional professional) {
        repository.delete(professional.getFundraiserId());
        logger.debug("DELETE | PostgreSQL | removed | fundraiser_professional id: {} fundraiser id: {} ", professional.getProfessionalId(), professional.getFundraiserId());
    }

}
