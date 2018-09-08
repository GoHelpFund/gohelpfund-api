package com.gohelpfund.api.v1.fundraisers.repository;

import com.gohelpfund.api.v1.fundraisers.model.FundraiserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraiserStatusRepository extends JpaRepository<FundraiserStatus, String> {
    FundraiserStatus findByFundraiserId(String fundraiserId);
    FundraiserStatus findByStatusId(String statusId);
}
