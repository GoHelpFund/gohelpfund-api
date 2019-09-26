package com.gohelpfund.api.v1.donation_service.wallets.repository.bitcoin;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin.BitcoinWalletBacker;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletBacker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitcoinWalletBackersRepository extends JpaRepository<BitcoinWalletBacker, String> {
    BitcoinWalletBacker findByBackerId(String backerId);
    List<BitcoinWalletBacker> findByBitcoinId(String bitcoinId);
}
