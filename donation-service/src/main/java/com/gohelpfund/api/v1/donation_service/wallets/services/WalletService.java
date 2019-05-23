package com.gohelpfund.api.v1.donation_service.wallets.services;

import com.gohelpfund.api.v1.donation_service.wallets.models.Donation;
import com.gohelpfund.api.v1.donation_service.wallets.models.HelpWalletBacker;
import com.gohelpfund.api.v1.donation_service.wallets.models.HelpWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.models.Wallet;
import com.gohelpfund.api.v1.donation_service.wallets.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WalletService {
    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private HelpWalletDetailsService helpWalletDetailsService;

    @Autowired
    private HelpWalletTransactionsService helpWalletTransactionsService;

    @Autowired
    private HelpWalletBackersService helpWalletBackersService;

    @Autowired
    private HelpWalletInsightService helpInsight;

    public Wallet getWalletById(String walletId) {
        Wallet wallet = walletRepository.findById(walletId);
        if (wallet == null) {
            logger.debug("GET | PostgreSQL | not found | wallet id: {}", walletId);
        } else {
            logger.debug("GET | PostgreSQL | found | wallet id: {}", wallet.getId());
            wallet
                    .withHelpWalletDetails(helpWalletDetailsService.getByHelpId(wallet.getHelpWallet().getHelpId()));
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
                    .withHelpWalletDetails(helpWalletDetailsService.getByHelpId(wallet.getHelpWallet().getHelpId()));
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
        Date currentDate = new Date();
        String entityId = donation.getEntity_id();
        String entityName = donation.getEntity_name();
        Wallet donatorWallet = getWalletByEntityId(entityId);
        String donatorHelpId = donatorWallet.getHelpWallet().getHelpId();

        Integer remaining = donatorWallet.getHelpWallet().getBalance() - donation.getAmount();

        if (remaining < 0) {
            throw new Exception("Balance is lower than donation amount");
        }

        Wallet campaignWallet = getWalletById(walletId);
        String campaignHelpId = campaignWallet.getHelpWallet().getHelpId();

        String donatorAddress = donatorWallet.getHelpWallet().getPublicKey();
        String donatorPrivateKey = donatorWallet.getHelpWallet().getPrivateKey();
        String campaignAddress = campaignWallet.getHelpWallet().getPublicKey();
        String txid = helpInsight.sendHelpCoins(donatorAddress, donatorPrivateKey, campaignAddress, donation.getAmount());
        helpWalletTransactionsService.save(donatorHelpId, currentDate, "sent", donation.getAmount(), donatorHelpId, campaignHelpId, entityName, donatorAddress, txid);

        donatorWallet.getHelpWallet().setBalance(remaining);

        helpWalletDetailsService.update(donatorWallet.getHelpWallet());
        helpWalletTransactionsService.save(campaignHelpId, currentDate, "received", donation.getAmount(), donatorHelpId, campaignHelpId, entityName, donatorAddress, txid);

        List<HelpWalletBacker> backers = helpWalletBackersService.getAll(campaignHelpId);
        boolean backerExists = backers.stream().anyMatch(b -> b.getFundraiser_id().equals(entityId));

        if (!backerExists) {
            HelpWalletBacker backer = new HelpWalletBacker();
            backer.setHelpId(campaignHelpId);
            backer.setFundraiser_id(entityId);
            helpWalletBackersService.save(backer);
        }
        campaignWallet.getHelpWallet().setBalance(campaignWallet.getHelpWallet().getBalance() + donation.getAmount());

        helpWalletDetailsService.update(campaignWallet.getHelpWallet());
        campaignWallet.withHelpWalletDetails(helpWalletDetailsService.getByHelpId(campaignHelpId));
        logger.debug("PUT | PostgreSQL | updated | wallet id: {} ", walletId);

        return campaignWallet;
    }




}
