package com.gohelpfund.api.v1.donation_service.wallets.services.promise;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletBacker;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise.PromiseWalletBacker;
import com.gohelpfund.api.v1.donation_service.wallets.repository.promise.PromiseWalletBackersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PromiseWalletBackersService {
    private static final Logger logger = LoggerFactory.getLogger(PromiseWalletTransactionsService.class);
    @Autowired
    private PromiseWalletBackersRepository repository;

    public List<PromiseWalletBacker> getAll(String promiseId) {
        List<PromiseWalletBacker> backers = repository.findByPromiseId(promiseId);

        if (backers != null) {
            logger.debug("GET | PostgreSQL | found | promise_wallet_backers  size: {}", backers.size());
        } else {
            logger.debug("GET | PostgreSQL | empty | promise_wallet_backers size: 0");
        }

        return backers;
    }

    public PromiseWalletBacker save(PromiseWalletBacker backer) {
        backer.setBackerId(UUID.randomUUID().toString());

        PromiseWalletBacker newBacker = repository.save(backer);
        logger.debug("POST | PostgreSQL | saved | promise_wallet_backer id: {} ", newBacker.getBackerId());
        return newBacker;
    }

}
