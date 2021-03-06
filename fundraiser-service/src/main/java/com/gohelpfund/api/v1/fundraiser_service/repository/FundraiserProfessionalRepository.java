package com.gohelpfund.api.v1.fundraiser_service.repository;

import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.professional.FundraiserProfessional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraiserProfessionalRepository extends JpaRepository<FundraiserProfessional, String> {
    FundraiserProfessional findByFundraiserId(String fundraiserId);
    FundraiserProfessional findByProfessionalId(String professionalId);
}
