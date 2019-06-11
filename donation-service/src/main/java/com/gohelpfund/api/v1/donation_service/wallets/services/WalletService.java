package com.gohelpfund.api.v1.donation_service.wallets.services;

import com.gohelpfund.api.v1.donation_service.wallets.models.Donation;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletBacker;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.Wallet;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise.PromiseWalletBacker;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise.PromiseWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.repository.WalletRepository;
import com.gohelpfund.api.v1.donation_service.wallets.services.help.HelpWalletBackersService;
import com.gohelpfund.api.v1.donation_service.wallets.services.help.HelpWalletDetailsService;
import com.gohelpfund.api.v1.donation_service.wallets.services.help.api.HelpWalletInsightService;
import com.gohelpfund.api.v1.donation_service.wallets.services.help.HelpWalletTransactionsService;
import com.gohelpfund.api.v1.donation_service.wallets.services.promise.PromiseWalletBackersService;
import com.gohelpfund.api.v1.donation_service.wallets.services.promise.PromiseWalletDetailsService;
import com.gohelpfund.api.v1.donation_service.wallets.services.promise.PromiseWalletTransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WalletService {
    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    private final WalletRepository walletRepository;

    private final HelpWalletDetailsService helpWalletDetailsService;
    private final PromiseWalletDetailsService promiseWalletDetailsService;

    private final HelpWalletTransactionsService helpWalletTransactionsService;
    private final PromiseWalletTransactionsService promiseWalletTransactionsService;

    private final HelpWalletBackersService helpWalletBackersService;
    private final PromiseWalletBackersService promiseWalletBackersService;

    private final HelpWalletInsightService helpInsight;

    public WalletService(WalletRepository walletRepository, HelpWalletDetailsService helpWalletDetailsService,
                         PromiseWalletDetailsService promiseWalletDetailsService,
                         HelpWalletTransactionsService helpWalletTransactionsService,
                         PromiseWalletTransactionsService promiseWalletTransactionsService,
                         HelpWalletBackersService helpWalletBackersService,
                         PromiseWalletBackersService promiseWalletBackersService,
                         HelpWalletInsightService helpInsight) {
        this.walletRepository = walletRepository;
        this.helpWalletDetailsService = helpWalletDetailsService;
        this.promiseWalletDetailsService = promiseWalletDetailsService;
        this.helpWalletTransactionsService = helpWalletTransactionsService;
        this.promiseWalletTransactionsService = promiseWalletTransactionsService;
        this.helpWalletBackersService = helpWalletBackersService;
        this.promiseWalletBackersService = promiseWalletBackersService;
        this.helpInsight = helpInsight;
    }

    public Wallet getWalletById(String walletId) {
        Wallet wallet = walletRepository.findById(walletId);
        if (wallet == null) {
            logger.debug("GET | PostgreSQL | not found | wallet id: {}", walletId);
        } else {
            logger.debug("GET | PostgreSQL | found | wallet id: {}", wallet.getId());
            HelpWalletDetails help = helpWalletDetailsService.getByHelpId(wallet.getHelpId());
            PromiseWalletDetails promise = promiseWalletDetailsService.getByPromiseId(wallet.getPromiseId());

            wallet
                    .withHelpWalletDetails(help)
                    .withPromiseWalletDetails(promise);
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
                    .withHelpWalletDetails(helpWalletDetailsService.getByHelpId(wallet.getHelpId()))
                    .withPromiseWalletDetails(promiseWalletDetailsService.getByPromiseId(wallet.getPromiseId()));
        }

        return wallet;
    }

    public Wallet createWallet(String entityId, String type, String source) {
        String id = UUID.randomUUID().toString();

        Wallet wallet = new Wallet();
        PromiseWalletDetails promise = promiseWalletDetailsService.saveOne(entityId, type);

        wallet
                .withWalletId(id)
                .withPromiseId(promise.getPromiseId())
                .withEntityId(entityId)
                .withType(type)
                .withPromiseWalletDetails(promise);

        if (source != null && !source.equals("event")) {
            HelpWalletDetails help = helpWalletDetailsService.saveOne(entityId, type);
            wallet
                    .withHelpId(help.getHelpId())
                    .withHelpWalletDetails(help);
        }

        Wallet newWallet = walletRepository.save(wallet);
        logger.debug("POST | PostgreSQL | created | wallet id: {} ", newWallet.getId());

        return newWallet;
    }

    public Wallet saveWallet(Wallet wallet) {
        String id = UUID.randomUUID().toString();

        HelpWalletDetails help = helpWalletDetailsService.update(wallet.getHelpWallet());
        PromiseWalletDetails promise = promiseWalletDetailsService.update(wallet.getPromiseWallet());

        wallet
                .withWalletId(id)
                .withEntityId(wallet.getEntityId())
                .withHelpWalletDetails(help)
                .withPromiseWalletDetails(promise);

        Wallet newWallet = walletRepository.save(wallet);
        logger.debug("POST | PostgreSQL | created | wallet id: {} ", newWallet.getId());

        return newWallet;
    }

    public Wallet updateWallet(String walletId, Donation donation) throws Exception {
        Date currentDate = new Date();
        String entityId = donation.getEntity_id();
        String entityName = donation.getEntity_name();

        Wallet donatorWallet = getWalletByEntityId(entityId);
        Wallet receiverWallet = getWalletById(walletId);

        if (receiverWallet.getType().equalsIgnoreCase("campaign")) {
            String receiverHelpId = receiverWallet.getHelpId();
            String donatorHelpId = donatorWallet.getHelpId();

            Integer remaining = donatorWallet.getHelpWallet().getBalance() - donation.getAmount();

            if (remaining < 0) {
                throw new Exception("Balance is lower than donation amount");
            }


            String donatorAddress = donatorWallet.getHelpWallet().getPublicKey();
            String donatorPrivateKey = donatorWallet.getHelpWallet().getPrivateKey();
            String receiverAddress = receiverWallet.getHelpWallet().getPublicKey();
            String txid = helpInsight.sendHelpCoins(donatorAddress, donatorPrivateKey, receiverAddress, donation.getAmount());
            helpWalletTransactionsService.save(donatorHelpId, currentDate, "sent", donation.getAmount(), donatorHelpId, receiverHelpId, entityName, donatorAddress, txid);

            donatorWallet.getHelpWallet().setBalance(remaining);

            helpWalletDetailsService.update(donatorWallet.getHelpWallet());
            helpWalletTransactionsService.save(receiverHelpId, currentDate, "received", donation.getAmount(), donatorHelpId, receiverHelpId, entityName, donatorAddress, txid);

            List<HelpWalletBacker> backers = helpWalletBackersService.getAll(receiverHelpId);
            boolean backerExists = backers.stream().anyMatch(b -> b.getFundraiser_id().equals(entityId));

            if (!backerExists) {
                HelpWalletBacker backer = new HelpWalletBacker();
                backer.setHelpId(receiverHelpId);
                backer.setFundraiser_id(entityId);
                helpWalletBackersService.save(backer);
            }
            receiverWallet.getHelpWallet().setBalance(receiverWallet.getHelpWallet().getBalance() + donation.getAmount());

            helpWalletDetailsService.update(receiverWallet.getHelpWallet());
            receiverWallet.withHelpWalletDetails(helpWalletDetailsService.getByHelpId(receiverHelpId));
            logger.debug("PUT | PostgreSQL | updated | wallet id: {} ", walletId);
        } else if (receiverWallet.getType().equalsIgnoreCase("event")) {
            String receiverPromiseId = receiverWallet.getPromiseId();
            String donatorPromiseId = donatorWallet.getPromiseId();

            Integer remaining = donatorWallet.getPromiseWallet().getBalance() - donation.getAmount();

            if (remaining < 0) {
                throw new Exception("Balance is lower than donation amount");
            }

            promiseWalletTransactionsService.save(donatorPromiseId, currentDate, "sent", donation.getAmount(), donatorPromiseId, receiverPromiseId, entityName);

            donatorWallet.getPromiseWallet().setBalance(remaining);

            promiseWalletDetailsService.update(donatorWallet.getPromiseWallet());
            promiseWalletTransactionsService.save(receiverPromiseId, currentDate, "received", donation.getAmount(), donatorPromiseId, receiverPromiseId, entityName);

            List<PromiseWalletBacker> backers = promiseWalletBackersService.getAll(receiverPromiseId);
            PromiseWalletBacker backerDAO = backers.stream()
                    .filter(b -> b.getFundraiser_id().equals(entityId))
                    .findAny()
                    .orElse(null);

            if (backerDAO == null) {
                PromiseWalletBacker backer = new PromiseWalletBacker();
                backer.setPromiseId(receiverPromiseId);
                backer.setFundraiser_id(entityId);
                backer.setTotalAmount(donation.getAmount());
                promiseWalletBackersService.save(backer);
            } else {
                backerDAO.setTotalAmount(backerDAO.getTotalAmount() + donation.getAmount());
                promiseWalletBackersService.save(backerDAO);
            }
            receiverWallet.getPromiseWallet().setBalance(receiverWallet.getPromiseWallet().getBalance() + donation.getAmount());

            promiseWalletDetailsService.update(receiverWallet.getPromiseWallet());
            receiverWallet.withPromiseWalletDetails(promiseWalletDetailsService.getByPromiseId(receiverPromiseId));
            logger.debug("PUT | PostgreSQL | updated | wallet id: {} ", walletId);
        }

        return receiverWallet;
    }


}
