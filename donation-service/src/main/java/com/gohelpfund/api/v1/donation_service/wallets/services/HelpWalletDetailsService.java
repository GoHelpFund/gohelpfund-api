package com.gohelpfund.api.v1.donation_service.wallets.services;

import com.gohelpfund.api.v1.donation_service.config.ServiceConfig;
import com.gohelpfund.api.v1.donation_service.wallets.models.CryptoCurrencyCredentials;
import com.gohelpfund.api.v1.donation_service.wallets.models.HelpWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.repository.HelpWalletDetailsRepository;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HelpWalletDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(HelpWalletDetailsService.class);

    @Autowired
    private HelpWalletDetailsRepository repository;

    @Autowired
    private HelpWalletTransactionsService donations;

    @Autowired
    private HelpWalletBackersService backers;

    @Autowired
    private HelpWalletInsightService insight;

    @Autowired
    ServiceConfig config;

    public HelpWalletDetails getByEntityId(String entityId) {
        HelpWalletDetails helpWalletDetails = repository.findByEntityId(entityId);

        if (helpWalletDetails != null) {
            logger.debug("GET | PostgreSQL | found | help_wallet_details id: {}", helpWalletDetails.getHelpId());
        } else {
            logger.debug("GET | PostgreSQL | empty | help_wallet_details entity_id: {}", entityId);
        }

        return helpWalletDetails;
    }

    public HelpWalletDetails getByHelpId(String helpId) {
        HelpWalletDetails helpWalletDetails = repository.findByHelpId(helpId);

        if (helpWalletDetails != null) {
            logger.debug("GET | PostgreSQL | found | help_wallet_details id: {}", helpWalletDetails.getHelpId());
            helpWalletDetails.setTransactions(donations.getAll(helpId));
            helpWalletDetails.setBackers(backers.getAll(helpId));
        } else {
            logger.debug("GET | PostgreSQL | empty | help_wallet_details id: {}", helpId);
        }
        return helpWalletDetails;
    }

    public HelpWalletDetails update(HelpWalletDetails helpWallet) {
        HelpWalletDetails newHelpWalletDetails = repository.save(helpWallet);
        logger.debug("POST | PostgreSQL | updated | help_wallet_details id: {} ", helpWallet.getHelpId());

        return newHelpWalletDetails;
    }

    public HelpWalletDetails saveOne(String entityId, String type) {
        String id = UUID.randomUUID().toString();

        HelpWalletDetails helpWalletDetails = new HelpWalletDetails();
        CryptoCurrencyCredentials credentials = generateCryptoCurrencyCredentials();

        int balance = 0;
        if (type.equalsIgnoreCase("fundraiser")){
            balance = 10;
            String topUpAddress = config.getTopUpHelpAddr();
            String topUpPrivateKey = config.getTopUpHelpPk();
            insight.sendHelpCoins(topUpAddress, topUpPrivateKey, credentials.getPublicKey(), balance);
        }
        helpWalletDetails
                .withId(id)
                .withEnityId(entityId)
                .withBalance(balance)
                .withPublicKey(credentials.getPublicKey())
                .withPrivateKey(credentials.getPrivateKey());

        HelpWalletDetails newHelpWalletDetails = repository.save(helpWalletDetails);
        logger.debug("POST | PostgreSQL | created | help_wallet_details id: {} ", newHelpWalletDetails.getHelpId());

        return newHelpWalletDetails;
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
