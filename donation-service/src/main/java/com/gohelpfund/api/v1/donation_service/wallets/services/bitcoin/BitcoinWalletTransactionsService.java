package com.gohelpfund.api.v1.donation_service.wallets.services.bitcoin;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin.BitcoinWalletTransaction;
import com.gohelpfund.api.v1.donation_service.wallets.repository.bitcoin.BitcoinWalletTransactionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BitcoinWalletTransactionsService {
    private static final Logger logger = LoggerFactory.getLogger(BitcoinWalletTransactionsService.class);
    @Autowired
    private BitcoinWalletTransactionsRepository repository;

    public List<BitcoinWalletTransaction> getAll(String bitcoinId) {
        List<BitcoinWalletTransaction> transactions = repository.findByBitcoinId(bitcoinId);

        if (transactions != null) {
            logger.debug("GET | PostgreSQL | found | bitcoin_wallet_donations  size: {}", transactions.size());
        } else {
            logger.debug("GET | PostgreSQL | empty | bitcoin_wallet_donations size: 0");
        }

        return transactions;
    }

    public BitcoinWalletTransaction save(String bitcoinId, Date date, String type, Integer amount, String senderBitcoinId,
                                      String receiverBitcoinId, String senderName, String senderAddress, String txid) {
        BitcoinWalletTransaction transaction = new BitcoinWalletTransaction();
        transaction.withId(UUID.randomUUID().toString())
                .withBitcoinId(bitcoinId)
                .withDate(date)
                .withType(type)
                .withBlockchainTransactionId(txid)
                .withAmount(amount)
                .withSenderBitcoinId(senderBitcoinId)
                .withReceiverBitcoinId(receiverBitcoinId)
                .withSenderName(senderName)
                .withSenderAddress(senderAddress);

        BitcoinWalletTransaction newTransaction = repository.save(transaction);

        logger.debug("POST | PostgreSQL | saved | bitcoin_wallet_transaction id: {} ", newTransaction.getTransactionId());
        return newTransaction;
    }

}
