package com.gohelpfund.api.v1.donation_service.wallets.services.promise;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise.PromiseWalletTransaction;
import com.gohelpfund.api.v1.donation_service.wallets.repository.promise.PromiseWalletTransactionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromiseWalletTransactionsService {
    private static final Logger logger = LoggerFactory.getLogger(PromiseWalletTransactionsService.class);
    @Autowired
    private PromiseWalletTransactionsRepository repository;

    public List<PromiseWalletTransaction> getAll(String promiseId) {
        List<PromiseWalletTransaction> transactions = repository.findByPromiseId(promiseId);

        if (transactions != null) {
            logger.debug("GET | PostgreSQL | found | promise_wallet_donations  size: {}", transactions.size());
        } else {
            logger.debug("GET | PostgreSQL | empty | promise_wallet_donations size: 0");
        }

        return transactions;
    }

    public PromiseWalletTransaction save(String promiseId, Date date, String type, Double amount, String senderPromiseId,
                                      String receiverPromiseId, String senderName) {
        PromiseWalletTransaction transaction = new PromiseWalletTransaction();
        transaction.withId(UUID.randomUUID().toString())
                .withHelpId(promiseId)
                .withDate(date)
                .withType(type)
                .withAmount(amount)
                .withSenderHelpId(senderPromiseId)
                .withReceiverHelpId(receiverPromiseId)
                .withSenderName(senderName);

        PromiseWalletTransaction newTransaction = repository.save(transaction);

        logger.debug("POST | PostgreSQL | saved | help_wallet_transaction id: {} ", newTransaction.getTransactionId());
        return newTransaction;
    }

}
