package com.gohelpfund.api.v1.donation_service.wallets.services.promise;

import com.gohelpfund.api.v1.donation_service.config.ServiceConfig;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise.PromiseWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.repository.promise.PromiseWalletDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PromiseWalletDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(PromiseWalletDetailsService.class);

    private final PromiseWalletDetailsRepository repository;
    private final PromiseWalletTransactionsService donations;
    private final PromiseWalletBackersService backers;
    private final ServiceConfig config;

    @Autowired
    public PromiseWalletDetailsService(PromiseWalletDetailsRepository repository,
                                       PromiseWalletTransactionsService donations,
                                       PromiseWalletBackersService backers,
                                       ServiceConfig config) {
        this.repository = repository;
        this.donations = donations;
        this.backers = backers;
        this.config = config;
    }

    public PromiseWalletDetails getByEntityId(String entityId) {
        PromiseWalletDetails promiseWalletDetails = repository.findByEntityId(entityId);

        if (promiseWalletDetails != null) {
            logger.debug("GET | PostgreSQL | found | promise_wallet_details id: {}", promiseWalletDetails.getPromiseId());
        } else {
            logger.debug("GET | PostgreSQL | empty | promise_wallet_details entity_id: {}", entityId);
        }

        return promiseWalletDetails;
    }

    public PromiseWalletDetails getByPromiseId(String promiseId) {
        PromiseWalletDetails promiseWalletDetails = repository.findByPromiseId(promiseId);

        if (promiseWalletDetails != null) {
            logger.debug("GET | PostgreSQL | found | promise_wallet_details id: {}", promiseWalletDetails.getPromiseId());
            promiseWalletDetails.setTransactions(donations.getAll(promiseId));
            promiseWalletDetails.setBackers(backers.getAll(promiseId));
        } else {
            logger.debug("GET | PostgreSQL | empty | promise_wallet_details id: {}", promiseId);
        }
        return promiseWalletDetails;
    }

    public PromiseWalletDetails update(PromiseWalletDetails promiseWallet) {
        PromiseWalletDetails newPromiseWalletDetails = repository.save(promiseWallet);
        logger.debug("POST | PostgreSQL | updated | promise_wallet_details id: {} ", promiseWallet.getPromiseId());

        return newPromiseWalletDetails;
    }

    public PromiseWalletDetails saveOne(String entityId, String type) {
        String id = UUID.randomUUID().toString();

        PromiseWalletDetails promiseWalletDetails = new PromiseWalletDetails();

        double balance = 0;
        if (type.equalsIgnoreCase("fundraiser")){
            balance = 1000000;
        }
        promiseWalletDetails
                .withId(id)
                .withEnityId(entityId)
                .withBalance(balance);

        PromiseWalletDetails newPromiseWalletDetails = repository.save(promiseWalletDetails);
        logger.debug("POST | PostgreSQL | created | promise_wallet_details id: {} ", newPromiseWalletDetails.getPromiseId());

        return newPromiseWalletDetails;
    }
}
