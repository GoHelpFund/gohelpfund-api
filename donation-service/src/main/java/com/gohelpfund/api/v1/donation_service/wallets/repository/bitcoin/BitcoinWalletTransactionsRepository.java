package com.gohelpfund.api.v1.donation_service.wallets.repository.bitcoin;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin.BitcoinWalletTransaction;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitcoinWalletTransactionsRepository extends JpaRepository<BitcoinWalletTransaction, String> {
    BitcoinWalletTransaction findByTransactionId(String transactionId);
    List<BitcoinWalletTransaction> findByBitcoinId(String bitcoinId);
}
