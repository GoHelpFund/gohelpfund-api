package com.gohelpfund.api.v1.donation_service.wallets.services;

import com.gohelpfund.api.v1.donation_service.wallets.models.CryptoCurrencyCredentials;
import com.gohelpfund.api.v1.donation_service.wallets.models.HelpWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.repository.HelpWalletDetailsRepository;
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

    public HelpWalletDetails getByEntityId(String entityId) {
        HelpWalletDetails helpWalletDetails = repository.findByEntityId(entityId);

        if (helpWalletDetails != null) {
            logger.debug("GET | PostgreSQL | found | help_wallet_details id: {}", helpWalletDetails.getId());
        } else {
            logger.debug("GET | PostgreSQL | empty | help_wallet_details size: {}", entityId);
        }

        return helpWalletDetails;
    }

    public HelpWalletDetails getByHelpId(String helpId) {
        HelpWalletDetails helpWalletDetails = repository.findById(helpId);

        if (helpWalletDetails != null) {
            logger.debug("GET | PostgreSQL | found | help_wallet_details id: {}", helpWalletDetails.getId());
        } else {
            logger.debug("GET | PostgreSQL | empty | help_wallet_details size: {}", helpId);
        }

        return helpWalletDetails;
    }

    public HelpWalletDetails update(HelpWalletDetails helpWallet) {
        HelpWalletDetails newHelpWalletDetails = repository.save(helpWallet);
        logger.debug("POST | PostgreSQL | updated | help_wallet_details id: {} ", helpWallet.getId());

        return newHelpWalletDetails;
    }

    public HelpWalletDetails saveOne(String entityId, String type) {
        String id = UUID.randomUUID().toString();

        int balance = 0;
        if (type.equalsIgnoreCase("fundraiser")){
            balance = 100;
        }

        HelpWalletDetails helpWalletDetails = new HelpWalletDetails();
        CryptoCurrencyCredentials credentials = generateCryptoCurrencyCredentials();

        helpWalletDetails
                .withId(id)
                .withEnityId(entityId)
                .withBalance(balance)
                .withPublicKey(credentials.getPublicKey())
                .withPrivateKey(credentials.getPrivateKey());

        HelpWalletDetails newHelpWalletDetails = repository.save(helpWalletDetails);
        logger.debug("POST | PostgreSQL | created | help_wallet_details id: {} ", newHelpWalletDetails.getId());

        return newHelpWalletDetails;
    }

    private CryptoCurrencyCredentials generateCryptoCurrencyCredentials(){
        CryptoCurrencyCredentials credentials = new CryptoCurrencyCredentials();
        credentials.setPublicKey("XpLRm8JgdejrDgpRxnS5Fxp6hqKa3riU9r");
        credentials.setPrivateKey("X");

        return credentials;
    }


}
