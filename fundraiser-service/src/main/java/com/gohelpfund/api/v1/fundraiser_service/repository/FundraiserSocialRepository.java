package com.gohelpfund.api.v1.fundraiser_service.repository;

import com.gohelpfund.api.v1.fundraiser_service.model.fundraiser.social.FundraiserSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraiserSocialRepository extends JpaRepository<FundraiserSocial, String> {
    FundraiserSocial findByFundraiserId(String fundraiserId);
    FundraiserSocial findBySocialId(String socialId);
}
