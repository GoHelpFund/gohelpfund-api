package com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction;

import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.SigHashType;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.crypto.PrivateKey;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.types.OpSize;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.ByteBuffer;

public interface WitnessProducer {

    byte[] produceWitness(byte[] sigHash, PrivateKey key);

    static WitnessProducer getInstance(boolean segwit) {
        if (segwit) {
            return (sigHash, key) -> {
                ByteBuffer result = new ByteBuffer();

                result.append((byte) 0x02);

                ByteBuffer sign = new ByteBuffer(key.sign(sigHash));
                sign.append(SigHashType.ALL.asByte());
                result.append(OpSize.ofInt(sign.size()).getSize());
                result.append(sign.bytes());

                byte[] pubKey = key.getPublicKey();
                result.append(OpSize.ofInt(pubKey.length).getSize());
                result.append(pubKey);

                return result.bytes();
            };
        } else {
            return (sigHash, key) -> new byte[] {(byte) 0x00};
        }
    }
}
