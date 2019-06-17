package com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction;


import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.OpCodes;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.SigHashType;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.crypto.PrivateKey;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.types.OpSize;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.ByteBuffer;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.HashUtils;

public interface ScriptSigProducer {

    byte[] produceScriptSig(byte[] sigHash, PrivateKey key);

    static ScriptSigProducer getInstance(boolean segwit) {
        if (segwit) {
            return (sigHash, key) -> {
                ByteBuffer result = new ByteBuffer();

                result.append(OpCodes.FALSE);
                result.append((byte) 0x14); //ripemd160 size
                result.append(HashUtils.ripemd160(HashUtils.sha256(key.getPublicKey())));
                result.putFirst(OpSize.ofInt(result.size()).getSize()); //PUSH DATA

                return result.bytes();
            };
        } else {
            return (sigHash, key) -> {
                ByteBuffer result = new ByteBuffer();

                result.append(key.sign(sigHash));
                result.append(SigHashType.ALL.asByte());

                result.putFirst(OpSize.ofInt(result.size()).getSize());

                byte[] publicKey = key.getPublicKey();
                result.append(OpSize.ofInt(publicKey.length).getSize());
                result.append(publicKey);

                return result.bytes();
            };
        }

    }
}
