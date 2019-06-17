package com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction;

import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.ErrorMessages;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.OpCodes;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.types.OpSize;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.ByteBuffer;

public interface ScriptPubKeyProducer {

    byte[] produceScript(byte[] hash);

    static ScriptPubKeyProducer getInstance(boolean mainNet, byte prefix) {
        if (prefix == (mainNet ? (byte) 0x4C : (byte) 0x6F)) {
            //P2PKH
            return hash -> {
                ByteBuffer lockingScript = new ByteBuffer();
                lockingScript.append(OpCodes.DUP, OpCodes.HASH160);
                lockingScript.append(OpSize.ofInt(hash.length).getSize());
                lockingScript.append(hash);
                lockingScript.append(OpCodes.EQUALVERIFY, OpCodes.CHECKSIG);
                return lockingScript.bytes();
            };
        } else if (prefix == (mainNet ? (byte) 0x10 : (byte) 0xC4)) {
            //P2SH
            return hash -> {
                ByteBuffer lockingScript = new ByteBuffer();
                lockingScript.append(OpCodes.HASH160);
                lockingScript.append(OpSize.ofInt(hash.length).getSize());
                lockingScript.append(hash);
                lockingScript.append(OpCodes.EQUAL);
                return lockingScript.bytes();
            };
        }

        throw new IllegalArgumentException(String.format(ErrorMessages.SPK_UNSUPPORTED_PRODUCER, mainNet, prefix));
    }

}

