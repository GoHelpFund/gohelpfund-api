package com.gohelpfund.api.v1.fundraisers.repository;

import com.gohelpfund.api.v1.fundraisers.model.Fundraiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FundraiserRepository extends JpaRepository<Fundraiser, String> {
    Optional<Fundraiser> findByFundraiserId(String fundraiserId);
}
