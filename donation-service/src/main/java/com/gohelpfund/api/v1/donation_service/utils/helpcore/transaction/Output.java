package com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction;

import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.ErrorMessages;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.types.ULong;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.types.VarInt;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.ByteBuffer;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.HexUtils;

public abstract class Output {

    protected final OutputType type;
    protected final long satoshi;

    protected Output(OutputType type, long satoshi) {
        validateData(type, satoshi);

        this.type = type;
        this.satoshi = satoshi;
    }

    byte[] serializeForSigHash() {
        ByteBuffer serialized = new ByteBuffer();

        serialized.append(ULong.of(satoshi).asLitEndBytes());

        byte[] lockingScript = getLockingScript();
        serialized.append(VarInt.of(lockingScript.length).asLitEndBytes());
        serialized.append(lockingScript);

        return serialized.bytes();
    }

    void fillTransaction(Transaction transaction) {
        transaction.addHeader("   Output (" + type.getDesc() + ")");

        transaction.addData("      Satoshi", ULong.of(satoshi).toString());
        byte[] lockingScript = getLockingScript();
        transaction.addData("      Lock length", VarInt.of(lockingScript.length).toString());
        transaction.addData("      Lock", HexUtils.asString(lockingScript));
    }

    protected abstract byte[] getLockingScript();

    long getSatoshi() {
        return satoshi;
    }

    private void validateData(OutputType type, long satoshi) {
        if (type == null) {
            throw new IllegalArgumentException(ErrorMessages.OUTPUT_TYPE_NULL);
        }
        if (satoshi <= 0) {
            throw new IllegalArgumentException(ErrorMessages.OUTPUT_AMOUNT_NOT_POSITIVE);
        }
    }

}
