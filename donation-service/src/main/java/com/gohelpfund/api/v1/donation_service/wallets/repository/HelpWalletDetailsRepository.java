package com.gohelpfund.api.v1.donation_service.wallets.repository;

import com.gohelpfund.api.v1.donation_service.wallets.models.HelpWalletDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpWalletDetailsRepository extends JpaRepository<HelpWalletDetails, String> {
    HelpWalletDetails findById(String helpId);
    HelpWalletDetails findByEntityId(String entityId);
}
