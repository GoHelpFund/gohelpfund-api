package com.gohelpfund.api.v1.donation_service.wallets.repository.help;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpWalletTransactionsRepository extends JpaRepository<HelpWalletTransaction, String> {
    HelpWalletTransaction findByTransactionId(String transactionId);
    List<HelpWalletTransaction> findByHelpId(String helpId);
}
