package com.gohelpfund.api.v1.donation_service.utils.helpcore.constant;

public interface OpCodes {

    byte FALSE = (byte) 0x00;

    byte RETURN = (byte) 0x6A;

    byte DUP = (byte) 0x76;

    byte HASH160 = (byte) 0xA9;

    byte EQUAL = (byte) 0x87;

    byte EQUALVERIFY = (byte) 0x88;

    byte CHECKSIG = (byte) 0xAC;

}
