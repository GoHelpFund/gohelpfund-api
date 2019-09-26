package com.gohelpfund.api.v1.donation_service.wallets.repository.bitcoin;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin.BitcoinWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitcoinWalletDetailsRepository extends JpaRepository<BitcoinWalletDetails, String> {
    BitcoinWalletDetails findByBitcoinId(String bitcoinId);
    BitcoinWalletDetails findByEntityId(String entityId);
}
