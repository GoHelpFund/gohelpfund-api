package com.gohelpfund.api.v1.fundraiser_service.repository;

import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.Fundraiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraiserRepository extends JpaRepository<Fundraiser, String> {
    Fundraiser findByFundraiserId(String fundraiserId);
}
