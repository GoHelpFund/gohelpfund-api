package com.gohelpfund.api.v1.donation_service.wallets.services;

import com.gohelpfund.api.v1.donation_service.wallets.models.HelpWalletBacker;
import com.gohelpfund.api.v1.donation_service.wallets.repository.HelpWalletBackersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HelpWalletBackersService {
    private static final Logger logger = LoggerFactory.getLogger(HelpWalletTransactionsService.class);
    @Autowired
    private HelpWalletBackersRepository repository;

    public List<HelpWalletBacker> getAll(String helpId) {
        List<HelpWalletBacker> backers = repository.findByHelpId(helpId);

        if (backers != null) {
            logger.debug("GET | PostgreSQL | found | help_wallet_backers  size: {}", backers.size());
        } else {
            logger.debug("GET | PostgreSQL | empty | help_wallet_backers size: 0");
        }

        return backers;
    }

    public HelpWalletBacker save(HelpWalletBacker backer) {
        backer.setBackerId(UUID.randomUUID().toString());

        HelpWalletBacker newBacker = repository.save(backer);
        logger.debug("POST | PostgreSQL | saved | help_wallet_backer id: {} ", newBacker.getBackerId());
        return newBacker;
    }

}
