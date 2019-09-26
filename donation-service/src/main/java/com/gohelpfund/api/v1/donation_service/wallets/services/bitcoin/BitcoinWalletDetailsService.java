package com.gohelpfund.api.v1.donation_service.wallets.services.bitcoin;

import com.gohelpfund.api.v1.donation_service.config.ServiceConfig;
import com.gohelpfund.api.v1.donation_service.wallets.models.CryptoCurrencyCredentials;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin.BitcoinWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.repository.bitcoin.BitcoinWalletDetailsRepository;
import com.gohelpfund.api.v1.donation_service.wallets.services.bitcoin.api.BitcoinWalletInsightService;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BitcoinWalletDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(BitcoinWalletDetailsService.class);

    @Autowired
    private BitcoinWalletDetailsRepository repository;

    @Autowired
    private BitcoinWalletTransactionsService donations;

    @Autowired
    private BitcoinWalletBackersService backers;

    @Autowired
    private BitcoinWalletInsightService insight;

    @Autowired
    ServiceConfig config;

    public BitcoinWalletDetails getByEntityId(String entityId) {
        BitcoinWalletDetails bitcoinWalletDetails = repository.findByEntityId(entityId);

        if (bitcoinWalletDetails != null) {
            logger.debug("GET | PostgreSQL | found | bitcoin_wallet_details id: {}", bitcoinWalletDetails.getBitcoinId());
        } else {
            logger.debug("GET | PostgreSQL | empty | bitcoin_wallet_details entity_id: {}", entityId);
        }

        return bitcoinWalletDetails;
    }

    public BitcoinWalletDetails getByBitcoinId(String bitcoinId) {
        BitcoinWalletDetails bitcoinWalletDetails = repository.findByBitcoinId(bitcoinId);

        if (bitcoinWalletDetails != null) {
            logger.debug("GET | PostgreSQL | found | bitcoin_wallet_details id: {}", bitcoinWalletDetails.getBitcoinId());
            bitcoinWalletDetails.setTransactions(donations.getAll(bitcoinId));
            bitcoinWalletDetails.setBackers(backers.getAll(bitcoinId));
        } else {
            logger.debug("GET | PostgreSQL | empty | bitcoin_wallet_details id: {}", bitcoinId);
        }
        return bitcoinWalletDetails;
    }

    public BitcoinWalletDetails update(BitcoinWalletDetails bitcoinWallet) {
        BitcoinWalletDetails newBitcoinWalletDetails = repository.save(bitcoinWallet);
        logger.debug("POST | PostgreSQL | updated | bitcoin_wallet_details id: {} ", bitcoinWallet.getBitcoinId());

        return newBitcoinWalletDetails;
    }

    public BitcoinWalletDetails saveOne(String entityId, String type) {
        String id = UUID.randomUUID().toString();

        BitcoinWalletDetails bitcoinWalletDetails = new BitcoinWalletDetails();
        CryptoCurrencyCredentials credentials = generateCryptoCurrencyCredentials();

        double balance = 0;
        if (type.equalsIgnoreCase("fundraiser")){
          /*  balance = 10;
            String topUpAddress = config.getTopUpBitcoinAddr();
            String topUpPrivateKey = config.getTopUpBitcoinPk();
            insight.sendBitcoinCoins(topUpAddress, topUpPrivateKey, credentials.getPublicKey(), balance);
            */
        }
        bitcoinWalletDetails
                .withId(id)
                .withEnityId(entityId)
                .withBalance(balance)
                .withPublicKey(credentials.getPublicKey())
                .withPrivateKey(credentials.getPrivateKey());

        BitcoinWalletDetails newBitcoinWalletDetails = repository.save(bitcoinWalletDetails);
        logger.debug("POST | PostgreSQL | created | bitcoin_wallet_details id: {} ", newBitcoinWalletDetails.getBitcoinId());

        return newBitcoinWalletDetails;
    }

    private CryptoCurrencyCredentials generateCryptoCurrencyCredentials(){
        CryptoCurrencyCredentials credentials = new CryptoCurrencyCredentials();
        NetworkParameters params = MainNetParams.get();
        Wallet wallet = new Wallet(params);
        credentials.setPublicKey(wallet.currentReceiveAddress().toString());
        credentials.setPrivateKey(wallet.currentReceiveKey().getPrivateKeyAsWiF(MainNetParams.get()));

        return credentials;
    }



}
