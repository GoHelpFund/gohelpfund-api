package com.gohelpfund.api.v1.donation_service.wallets.services;

import com.gohelpfund.api.v1.donation_service.wallets.models.Donation;
import com.gohelpfund.api.v1.donation_service.wallets.models.HelpWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.models.Wallet;
import com.gohelpfund.api.v1.donation_service.wallets.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService {
    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private HelpWalletDetailsService helpWalletDetailsService;

    public Wallet getWalletById(String walletId) {
        Wallet wallet = walletRepository.findById(walletId);
        if (wallet == null) {
            logger.debug("GET | PostgreSQL | not found | wallet id: {}", walletId);
        } else {
            logger.debug("GET | PostgreSQL | found | wallet id: {}", wallet.getId());
            wallet
                    .withHelpWalletDetails(helpWalletDetailsService.getByEntityId(wallet.getEntityId()));
        }

        return wallet;
    }

    public Wallet getWalletByEntityId(String entityId) {
        Wallet wallet = walletRepository.findByEntityId(entityId);
        if (wallet == null) {
            logger.debug("GET | PostgreSQL | not found | entity id: {}", entityId);
        } else {
            logger.debug("GET | PostgreSQL | found | entity id: {}", wallet.getEntityId());
            wallet
                    .withHelpWalletDetails(helpWalletDetailsService.getByHelpId(wallet.getHelpWallet().getId()));
        }

        return wallet;
    }

    public Wallet createWallet(String entityId, String type) {
        String id = UUID.randomUUID().toString();

        Wallet wallet = new Wallet();

        HelpWalletDetails help = helpWalletDetailsService.saveOne(entityId, type);

        wallet
                .withWalletId(id)
                .withEntityId(entityId)
                .withType(type)
                .withHelpWalletDetails(help);

        Wallet newWallet = walletRepository.save(wallet);
        logger.debug("POST | PostgreSQL | created | wallet id: {} ", newWallet.getId());

        return newWallet;
    }

    public Wallet saveWallet(Wallet wallet) {
        String id = UUID.randomUUID().toString();

        HelpWalletDetails help = helpWalletDetailsService.update(wallet.getHelpWallet());

        wallet
                .withWalletId(id)
                .withEntityId(wallet.getEntityId())
                .withHelpWalletDetails(help);

        Wallet newWallet = walletRepository.save(wallet);
        logger.debug("POST | PostgreSQL | created | wallet id: {} ", newWallet.getId());

        return newWallet;
    }

    public Wallet updateWallet(String walletId, Donation donation) throws Exception {
        Wallet donatorWallet = getWalletByEntityId(donation.getEntity_id());

        Integer remaining = donatorWallet.getHelpWallet().getBalance() - donation.getAmount();

        if(remaining < 0){
            throw new Exception("Balance is lower than donation amount");
        }

        donatorWallet.getHelpWallet().setBalance(remaining);
        helpWalletDetailsService.update(donatorWallet.getHelpWallet());

        Wallet campaignWallet = getWalletById(walletId);

        campaignWallet.getHelpWallet().setBalance(campaignWallet.getHelpWallet().getBalance() + donation.getAmount());

        HelpWalletDetails newHelpWallet = helpWalletDetailsService.update(campaignWallet.getHelpWallet());
        campaignWallet.withHelpWalletDetails(newHelpWallet);
        logger.debug("PUT | PostgreSQL | updated | wallet id: {} ", walletId);

        return campaignWallet;
    }


}
