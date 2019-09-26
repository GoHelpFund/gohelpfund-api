package com.gohelpfund.api.v1.donation_service.wallets.services.bitcoin.api;

import com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction.Transaction;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction.TransactionBuilder;
import com.gohelpfund.api.v1.donation_service.wallets.clients.BitcoinInsightRestTemplateClient;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightAddr;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightTx;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightTxResponse;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightTxVout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class BitcoinWalletInsightService {
    private static final Logger logger = LoggerFactory.getLogger(BitcoinWalletInsightService.class);

    /**
     * Number of decimals for one Bitcoin. This constant is useful for quick adapting to other coins because a lot of
     * constants derive from it.
     */
    public static final int SMALLEST_UNIT_EXPONENT = 8;

    @Autowired
    BitcoinInsightRestTemplateClient bitcoinInsightClient;

    private InsightAddr getInsightBitcoinAddr(String bitcoinAddress) {
        InsightAddr insightAddr = bitcoinInsightClient.getInsightBitcoinAddr(bitcoinAddress);

        if (insightAddr != null) {
            logger.debug("GET | /insight.bitpay.com/api/addr/{addr} | found | bitcoin address: {}", bitcoinAddress);
        } else {
            logger.debug("GET | /insight.bitpay.com/api/addr/{addr} | not found | bitcoin address: {}", bitcoinAddress);
        }
        return insightAddr;
    }

    private InsightTx getInsightBitcoinTx(String bitcoinTxId) {
        InsightTx insightTx = bitcoinInsightClient.getInsightBitcoinTx(bitcoinTxId);

        if (insightTx != null) {
            logger.debug("GET | /insight.bitpay.com/api/tx/{tx} | found | bitcoin tx id: {}", bitcoinTxId);
        } else {
            logger.debug("GET | /insight.bitpay.com/api/tx/{tx} | not found | bitcoin tx id: {}", bitcoinTxId);
        }
        return insightTx;
    }

    private InsightTxResponse setInsightBitcoinTx(String rawtxid) {
        InsightTxResponse insightBitcoinTx = bitcoinInsightClient.setTransaction(getHttpEntity(rawtxid));

        if (insightBitcoinTx != null) {
            logger.debug("POST | /insight.bitpay.com/api/tx/send | created | raw tx id: {}", rawtxid);
        } else {
            logger.debug("POST | /insight.bitpay.com/api/tx/send | creation failed | raw tx id: {}", rawtxid);
        }
        return insightBitcoinTx;
    }


    public String sendBitcoinCoins(String senderAddr, String senderPk, String receiverAddr, Double amount) {

        InsightAddr insightAddr = getInsightBitcoinAddr(senderAddr);
        String txId = insightAddr.getTransactions().get(0);

        InsightTxVout txVout = getInsightBitcoinTx(txId).getVout().stream()
                .filter(t -> t.getScriptPubKey().getAddresses().get(0).equals(senderAddr))
                .findFirst()
                .orElse(null);
        String hex = txVout.getScriptPubKey().getHex();
        int txOutNumber = txVout.getN();


        long receiverAmount = parseCoin(String.valueOf(amount)); // in satoshi = 1 bitcoin
        long senderBalance = insightAddr.getBalanceSat() + insightAddr.getUnconfirmedBalanceSat();
        long fee = 10000;
        long senderChange = senderBalance - receiverAmount - fee;

        TransactionBuilder builder = TransactionBuilder.create()
                .from(
                        txId,
                        txOutNumber,
                        hex,
                        senderBalance,
                        senderPk
                )
                .to(receiverAddr, receiverAmount)
                .to(senderAddr, senderChange)
                .withFee(fee);
        Transaction tx = builder.build();
        String rawTxId = tx.getRawTransaction();

        return setInsightBitcoinTx(rawTxId).getTxid();
    }

    private HttpEntity<Map<String, String>> getHttpEntity(String txid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, String> map = new HashMap<>();
        map.put("rawtx", txid);

        return new HttpEntity<>(map, headers);
    }

    /**
     * Parses an amount expressed in the way humans are used to.<p>
     * <p/>
     * This takes string in a format understood by {@link BigDecimal#BigDecimal(String)},
     * for example "0", "1", "0.10", "1.23E3", "1234.5E-5".
     *
     * @throws IllegalArgumentException if you try to specify fractional satoshis, or a value out of range.
     */
    private long parseCoin(final String str) {
        try {
            return new BigDecimal(str).movePointRight(SMALLEST_UNIT_EXPONENT).toBigIntegerExact().longValue();
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(e); // Repackage exception to honor method contract
        }
    }

}
