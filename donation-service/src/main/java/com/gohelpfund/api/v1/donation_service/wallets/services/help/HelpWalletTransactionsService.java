package com.gohelpfund.api.v1.donation_service.wallets.services.help;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletTransaction;
import com.gohelpfund.api.v1.donation_service.wallets.repository.help.HelpWalletTransactionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class HelpWalletTransactionsService {
    private static final Logger logger = LoggerFactory.getLogger(HelpWalletTransactionsService.class);
    @Autowired
    private HelpWalletTransactionsRepository repository;

    public List<HelpWalletTransaction> getAll(String helpId) {
        List<HelpWalletTransaction> transactions = repository.findByHelpId(helpId);

        if (transactions != null) {
            logger.debug("GET | PostgreSQL | found | help_wallet_donations  size: {}", transactions.size());
        } else {
            logger.debug("GET | PostgreSQL | empty | help_wallet_donations size: 0");
        }

        return transactions;
    }

    public HelpWalletTransaction save(String helpId, Date date, String type, Double amount, String senderHelpId,
                                      String receiverHelpId, String senderName, String senderAddress, String txid) {
        HelpWalletTransaction transaction = new HelpWalletTransaction();
        transaction.withId(UUID.randomUUID().toString())
                .withHelpId(helpId)
                .withDate(date)
                .withType(type)
                .withBlockchainTransactionId(txid)
                .withAmount(amount)
                .withSenderHelpId(senderHelpId)
                .withReceiverHelpId(receiverHelpId)
                .withSenderName(senderName)
                .withSenderAddress(senderAddress);

        HelpWalletTransaction newTransaction = repository.save(transaction);

        logger.debug("POST | PostgreSQL | saved | help_wallet_transaction id: {} ", newTransaction.getTransactionId());
        return newTransaction;
    }

}
