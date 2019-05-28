package com.gohelpfund.api.v1.donation_service.wallets.repository.help;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpWalletDetailsRepository extends JpaRepository<HelpWalletDetails, String> {
    HelpWalletDetails findByHelpId(String helpId);
    HelpWalletDetails findByEntityId(String entityId);
}
