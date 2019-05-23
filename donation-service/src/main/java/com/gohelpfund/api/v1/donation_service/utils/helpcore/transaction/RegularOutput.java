package com.gohelpfund.api.v1.donation_service.utils.helpcore.transaction;


import com.gohelpfund.api.v1.donation_service.utils.helpcore.constant.ErrorMessages;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.Base58CheckUtils;
import com.gohelpfund.api.v1.donation_service.utils.helpcore.util.ValidationUtils;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

class RegularOutput extends Output {

    private final boolean mainNet;
    private final String destination;
    private final byte[] decodedAddress;

    RegularOutput(boolean mainNet, long satoshi, String destination, OutputType type) {
        super(type, satoshi);

        validateOutputData(mainNet, destination);

        this.mainNet = mainNet;
        this.destination = destination;
        this.decodedAddress = Base58CheckUtils.decode(destination);
    }

    @Override
    protected byte[] getLockingScript() {
        return ScriptPubKeyProducer.getInstance(mainNet, decodedAddress[0])
                .produceScript(Arrays.copyOfRange(decodedAddress, 1, decodedAddress.length));
    }

    @Override
    public String toString() {
        return destination + " " + satoshi;
    }

    private void validateOutputData(boolean mainNet, String destination) {
        validateDestinationAddress(mainNet, destination);
    }

    private void validateDestinationAddress(boolean mainNet, String destination) {
        if (ValidationUtils.isEmpty(destination)) {
            throw new IllegalArgumentException(ErrorMessages.OUTPUT_ADDRESS_EMPTY);
        }

        if (!ValidationUtils.isBase58(destination)) {
            throw new IllegalArgumentException(ErrorMessages.OUTPUT_ADDRESS_NOT_BASE_58);
        }

        List<Character> prefixP2PKH = mainNet ? singletonList('X') : asList('m', 'n');
        List<Character> prefixP2SH = singletonList(mainNet ? 'X' : '2');

        char prefix = destination.charAt(0);
        if (!prefixP2PKH.contains(prefix) && !prefixP2SH.contains(prefix)) {
            throw new IllegalArgumentException(String.format(ErrorMessages.OUTPUT_ADDRESS_WRONG_PREFIX, prefixP2PKH, prefixP2SH));
        }
    }
}
