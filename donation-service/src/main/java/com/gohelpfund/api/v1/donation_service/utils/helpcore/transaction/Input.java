package com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction;

import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.ErrorMessages;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.crypto.PrivateKey;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.types.OpSize;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.types.UInt;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.types.VarInt;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.ByteBuffer;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.HexUtils;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.ValidationUtils;

class Input {

    private static final UInt SEQUENCE = UInt.of(0xFFFFFFFF);

    private final String transaction;
    private final int index;
    private final String lock;
    private final long satoshi;
    private final String wif;
    private final PrivateKey privateKey;

    Input(boolean mainNet, String transaction, int index, String lock, long satoshi, String wif) {
        validateInputData(transaction, index, lock, satoshi, wif);

        this.transaction = transaction;
        this.index = index;
        this.lock = lock;
        this.satoshi = satoshi;
        this.wif = wif;
        this.privateKey = PrivateKey.ofWif(mainNet, wif);
    }

    void fillTransaction(byte[] sigHash, Transaction transaction) {
        boolean segWit = isSegWit();

        transaction.addHeader(segWit ? "   Input (Segwit)" : "   Input");

        byte[] unlocking = ScriptSigProducer.getInstance(segWit).produceScriptSig(sigHash, privateKey);
        transaction.addData("      Transaction out", HexUtils.asString(getTransactionHashBytesLitEnd()));
        transaction.addData("      Tout index", UInt.of(index).toString());
        transaction.addData("      Unlock length", HexUtils.asString(VarInt.of(unlocking.length).asLitEndBytes()));
        transaction.addData("      Unlock", HexUtils.asString(unlocking));
        transaction.addData("      Sequence", SEQUENCE.toString());
    }

    byte[] getWitness(byte[] sigHash) {
        return WitnessProducer.getInstance(isSegWit()).produceWitness(sigHash, privateKey);
    }

    byte[] getTransactionHashBytesLitEnd() {
        return new ByteBuffer(HexUtils.asBytes(transaction)).bytesReversed();
    }

    long getSatoshi() {
        return satoshi;
    }

    boolean isSegWit() {
        return LockScriptType.forLock(lock).isSegWit();
    }

    int getIndex() {
        return index;
    }

    String getLock() {
        return lock;
    }

    PrivateKey getPrivateKey() {
        return privateKey;
    }

    UInt getSequence() {
        return SEQUENCE;
    }

    @Override
    public String toString() {
        return transaction +
                " " + index +
                " " + lock +
                " " + satoshi +
                " " + wif;
    }

    private void validateInputData(String transaction, int index, String lock, long satoshi, String wif) {
        validateTransactionId(transaction);
        validateOutputIndex(index);
        validateLockingScript(lock);
        validateAmount(satoshi);
        validateWif(wif);
    }

    private void validateTransactionId(String transaction) {
        if (ValidationUtils.isEmpty(transaction)) {
            throw new IllegalArgumentException(ErrorMessages.INPUT_TRANSACTION_EMPTY);
        }
        if (!ValidationUtils.isTransactionId(transaction)) {
            throw new IllegalArgumentException(ErrorMessages.INPUT_TRANSACTION_NOT_64_HEX);
        }
    }

    private void validateOutputIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException(ErrorMessages.INPUT_INDEX_NEGATIVE);
        }
    }

    private void validateLockingScript(String lock) {
        if (ValidationUtils.isEmpty(lock)) {
            throw new IllegalArgumentException(ErrorMessages.INPUT_LOCK_EMPTY);
        }
        if (!ValidationUtils.isHexString(lock)) {
            throw new IllegalArgumentException(ErrorMessages.INPUT_LOCK_NOT_HEX);
        }
        LockScriptType lockScriptType = LockScriptType.forLock(lock);
        if (LockScriptType.P2PKH.equals(lockScriptType)) {
            byte[] lockBytes = HexUtils.asBytes(lock);
            OpSize pubKeyHashSize = OpSize.ofByte(lockBytes[2]);
            if (pubKeyHashSize.getSize() != lockBytes.length - 5) {
                throw new IllegalArgumentException(String.format(ErrorMessages.INPUT_WRONG_PKH_SIZE, lock));
            }
        } else if (LockScriptType.P2SH.equals(lockScriptType)) {
            byte[] lockBytes = HexUtils.asBytes(lock);
            OpSize pubKeyHashSize = OpSize.ofByte(lockBytes[1]);
            if (pubKeyHashSize.getSize() != lockBytes.length - 3) {
                throw new IllegalArgumentException(String.format(ErrorMessages.INPUT_WRONG_RS_SIZE, lock));
            }
        } else {
            throw new IllegalArgumentException("Provided locking script is not P2PKH or P2SH [" + lock + "]");
        }
    }

    private void validateAmount(long satoshi) {
        if (satoshi <= 0) {
            throw new IllegalArgumentException(ErrorMessages.INPUT_AMOUNT_NOT_POSITIVE);
        }
    }

    private void validateWif(String wif) {
        if (ValidationUtils.isEmpty(wif)) {
            throw new IllegalArgumentException(ErrorMessages.INPUT_WIF_EMPTY);
        }
        if (!ValidationUtils.isBase58(wif)) {
            throw new IllegalArgumentException(ErrorMessages.INPUT_WIF_NOT_BASE_58);
        }
    }
}
