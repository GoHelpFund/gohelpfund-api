package com.gohelpfund.api.v1.fundraisers.repository;

import com.gohelpfund.api.v1.fundraisers.model.FundraiserSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraiserSocialRepository extends JpaRepository<FundraiserSocial, String> {
    FundraiserSocial findByFundraiserId(String fundraiserId);
    FundraiserSocial findBySocialId(String socialId);
}
