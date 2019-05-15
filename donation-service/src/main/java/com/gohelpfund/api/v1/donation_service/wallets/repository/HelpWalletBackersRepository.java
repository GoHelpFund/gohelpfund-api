package com.gohelpfund.api.v1.donation_service.wallets.repository;

import com.gohelpfund.api.v1.donation_service.wallets.models.HelpWalletBacker;
import com.gohelpfund.api.v1.donation_service.wallets.models.HelpWalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpWalletBackersRepository extends JpaRepository<HelpWalletBacker, String> {
    HelpWalletBacker findByBackerId(String backerId);
    List<HelpWalletBacker> findByHelpId(String helpId);
}
