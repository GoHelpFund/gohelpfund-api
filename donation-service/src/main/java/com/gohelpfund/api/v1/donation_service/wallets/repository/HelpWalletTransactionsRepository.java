package com.gohelpfund.api.v1.donation_service.wallets.repository;

import com.gohelpfund.api.v1.donation_service.wallets.models.HelpWalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpWalletTransactionsRepository extends JpaRepository<HelpWalletTransaction, String> {
    HelpWalletTransaction findByTransactionId(String transactionId);
    List<HelpWalletTransaction> findByHelpId(String helpId);
}
