package com.gohelpfund.api.v1.fundraiser_service.repository;

import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.status.FundraiserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraiserStatusRepository extends JpaRepository<FundraiserStatus, String> {
    FundraiserStatus findByFundraiserId(String fundraiserId);
    FundraiserStatus findByStatusId(String statusId);
}
