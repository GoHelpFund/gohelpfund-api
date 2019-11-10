package com.gohelpfund.api.v1.donation_service.wallets.services.bitcoin;

import com.gohelpfund.api.v1.donation_service.config.ServiceConfig;
import com.gohelpfund.api.v1.donation_service.wallets.models.CryptoCurrencyCredentials;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.bitcore.BitcoreAddr;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.bitcoin.BitcoinWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.repository.bitcoin.BitcoinWalletDetailsRepository;
import com.gohelpfund.api.v1.donation_service.wallets.services.bitcoin.api.BitcoinWalletBitcoreService;
import com.gohelpfund.api.v1.donation_service.wallets.services.bitcoin.api.BitcoinWalletInsightService;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.Wallet;
import org.helpj.core.Coin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BitcoinWalletDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(BitcoinWalletDetailsService.class);

    private final BitcoinWalletDetailsRepository repository;
    private final BitcoinWalletTransactionsService donations;
    private final BitcoinWalletBackersService backers;
    private final BitcoinWalletInsightService insight;
    private final BitcoinWalletBitcoreService bitcore;
    private final ServiceConfig config;

    @Autowired
    public BitcoinWalletDetailsService(BitcoinWalletDetailsRepository repository, BitcoinWalletTransactionsService donations, BitcoinWalletBackersService backers, BitcoinWalletInsightService insight, BitcoinWalletBitcoreService bitcore, ServiceConfig config) {
        this.repository = repository;
        this.donations = donations;
        this.backers = backers;
        this.insight = insight;
        this.bitcore = bitcore;
        this.config = config;
    }

    public BitcoinWalletDetails getByEntityId(String entityId) {
        BitcoinWalletDetails bitcoinWalletDetails = repository.findByEntityId(entityId);

        if (bitcoinWalletDetails != null) {
            logger.debug("GET | PostgreSQL | found | bitcoin_wallet_details id: {}", bitcoinWalletDetails.getBitcoinId());
        } else {
            logger.debug("GET | PostgreSQL | empty | bitcoin_wallet_details entity_id: {}", entityId);
        }

        return bitcoinWalletDetails;
    }

    public BitcoinWalletDetails getByBitcoinId(String bitcoinId) {
        BitcoinWalletDetails bitcoinWalletDetails = repository.findByBitcoinId(bitcoinId);

        if (bitcoinWalletDetails != null) {
            logger.debug("GET | PostgreSQL | found | bitcoin_wallet_details id: {}", bitcoinWalletDetails.getBitcoinId());
            BitcoreAddr bitcoreAddr = bitcore.getInsightBitcoinAddr(bitcoinWalletDetails.getPublicKey());
            long bal = bitcoreAddr.getBalance();
            logger.debug("bal | {}", bal);
            String valStr = Coin.valueOf(bal).toPlainString();
            logger.debug("valStr | {}", valStr);
            Double value = Double.valueOf(valStr);
            logger.debug("value | {}", value);
            bitcoinWalletDetails.setBalance(value);
        } else {
            logger.debug("GET | PostgreSQL | empty | bitcoin_wallet_details id: {}", bitcoinId);
        }
        return bitcoinWalletDetails;
    }

    public BitcoinWalletDetails update(BitcoinWalletDetails bitcoinWallet) {
        BitcoinWalletDetails newBitcoinWalletDetails = repository.save(bitcoinWallet);
        logger.debug("POST | PostgreSQL | updated | bitcoin_wallet_details id: {} ", bitcoinWallet.getBitcoinId());

        return newBitcoinWalletDetails;
    }

    public BitcoinWalletDetails saveOne(String entityId, String type) {
        String id = UUID.randomUUID().toString();

        BitcoinWalletDetails bitcoinWalletDetails = new BitcoinWalletDetails();
        CryptoCurrencyCredentials credentials = generateCryptoCurrencyCredentials();

        double balance = 0;
        if (type.equalsIgnoreCase("fundraiser")){
          /*  balance = 10;
            String topUpAddress = config.getTopUpBitcoinAddr();
            String topUpPrivateKey = config.getTopUpBitcoinPk();
            insight.sendBitcoinCoins(topUpAddress, topUpPrivateKey, credentials.getPublicKey(), balance);
            */
        }
        bitcoinWalletDetails
                .withId(id)
                .withEnityId(entityId)
                .withBalance(balance)
                .withPublicKey(credentials.getPublicKey())
                .withPrivateKey(credentials.getPrivateKey());

        BitcoinWalletDetails newBitcoinWalletDetails = repository.save(bitcoinWalletDetails);
        logger.debug("POST | PostgreSQL | created | bitcoin_wallet_details id: {} ", newBitcoinWalletDetails.getBitcoinId());

        return newBitcoinWalletDetails;
    }

    private CryptoCurrencyCredentials generateCryptoCurrencyCredentials(){
        CryptoCurrencyCredentials credentials = new CryptoCurrencyCredentials();
        NetworkParameters params = MainNetParams.get();
        Wallet wallet = new Wallet(params);
        credentials.setPublicKey(wallet.currentReceiveAddress().toString());
        credentials.setPrivateKey(wallet.currentReceiveKey().getPrivateKeyAsWiF(MainNetParams.get()));

        return credentials;
    }



}
