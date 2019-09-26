package com.gohelpfund.api.v1.donation_service.wallets.services.bitcoin;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin.BitcoinWalletBacker;
import com.gohelpfund.api.v1.donation_service.wallets.repository.bitcoin.BitcoinWalletBackersRepository;
import com.gohelpfund.api.v1.donation_service.wallets.repository.bitcoin.BitcoinWalletBackersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BitcoinWalletBackersService {
    private static final Logger logger = LoggerFactory.getLogger(BitcoinWalletTransactionsService.class);
    @Autowired
    private BitcoinWalletBackersRepository repository;

    public List<BitcoinWalletBacker> getAll(String bitcoinId) {
        List<BitcoinWalletBacker> backers = repository.findByBitcoinId(bitcoinId);

        if (backers != null) {
            logger.debug("GET | PostgreSQL | found | bitcoin_wallet_backers  size: {}", backers.size());
        } else {
            logger.debug("GET | PostgreSQL | empty | bitcoin_wallet_backers size: 0");
        }

        return backers;
    }

    public BitcoinWalletBacker save(BitcoinWalletBacker backer) {
        backer.setBackerId(UUID.randomUUID().toString());

        BitcoinWalletBacker newBacker = repository.save(backer);
        logger.debug("POST | PostgreSQL | saved | bitcoin_wallet_backer id: {} ", newBacker.getBackerId());
        return newBacker;
    }

}
