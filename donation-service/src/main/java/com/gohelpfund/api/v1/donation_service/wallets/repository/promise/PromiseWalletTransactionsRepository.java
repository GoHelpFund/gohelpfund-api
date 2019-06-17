package com.gohelpfund.api.v1.donation_service.wallets.repository.promise;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletTransaction;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise.PromiseWalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromiseWalletTransactionsRepository extends JpaRepository<PromiseWalletTransaction, String> {
    PromiseWalletTransaction findByTransactionId(String transactionId);
    List<PromiseWalletTransaction> findByPromiseId(String promiseId);
}
