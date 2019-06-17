package com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction;

import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.ErrorMessages;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.OpCodes;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.HexUtils;

public enum LockScriptType {
    P2PKH(false), P2SH(true);

    private final boolean segWit;

    LockScriptType(boolean segWit) {
        this.segWit = segWit;
    }

    public boolean isSegWit() {
        return segWit;
    }

    public static LockScriptType forLock(String lock) {
        byte[] lockBytes = HexUtils.asBytes(lock);
        if (isP2PKH(lockBytes)) {
            return P2PKH;
        } else if (isP2SH(lockBytes)) {
            return P2SH;
        } else {
            throw new IllegalArgumentException(String.format(ErrorMessages.INPUT_LOCK_WRONG_FORMAT, lock));
        }
    }

    private static boolean isP2SH(byte[] lockBytes) {
        return lockBytes[0] == OpCodes.HASH160
                && lockBytes[lockBytes.length - 1] == OpCodes.EQUAL;
    }

    private static boolean isP2PKH(byte[] lockBytes) {
        return lockBytes[0] == OpCodes.DUP
                && lockBytes[1] == OpCodes.HASH160
                && lockBytes[lockBytes.length - 2] == OpCodes.EQUALVERIFY
                && lockBytes[lockBytes.length - 1] == OpCodes.CHECKSIG;
    }
}
