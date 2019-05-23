package com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction;

import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.ErrorMessages;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.OpCodes;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.types.OpSize;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.ByteBuffer;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.HexUtils;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.ValidationUtils;

public class UnspendableOutput extends Output {

    private static final int MAX_DATA_SIZE = 40;

    private final String data;
    private final long satoshi;
    private final byte[] dataBytes;

    public UnspendableOutput(String data, long satoshi) {
        super(OutputType.UNSPENDABLE, satoshi);

        validateData(data);

        this.data = data;
        this.satoshi = satoshi;
        this.dataBytes = HexUtils.asBytes(data);
    }

    long getSatoshi() {
        return satoshi;
    }

    @Override
    public String toString() {
        return "[U] " + data + " " + satoshi;
    }

    @Override
    protected byte[] getLockingScript() {
        ByteBuffer result = new ByteBuffer();
        result.append(OpCodes.RETURN);
        result.append(OpSize.ofInt(dataBytes.length).getSize());
        result.append(dataBytes);
        return result.bytes();
    }

    private void validateData(String data) {
        if (ValidationUtils.isEmpty(data)) {
            throw new IllegalArgumentException(ErrorMessages.OUTPUT_DATA_IS_EMPTY);
        }

        if (!ValidationUtils.isHexString(data)) {
            throw new IllegalArgumentException(String.format(ErrorMessages.OUTPUT_DATA_IS_NOT_HEX));
        }

        byte[] bytes = HexUtils.asBytes(data);
        if (bytes.length > MAX_DATA_SIZE) {
            throw new IllegalArgumentException(String.format(ErrorMessages.OUTPUT_DATA_BYTES_HUGE, bytes.length, MAX_DATA_SIZE));
        }
    }

}
