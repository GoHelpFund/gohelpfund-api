package com.gohelpfund.api.v1.donation_service.utils.helpcore.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;

public class ApplicationRandom {

    private static Random random;

    static {
        reset();
    }

    private ApplicationRandom() { }

    public static Random get() {
        return random;
    }

    public static void reset() {
        String runtime = "true";
        if (runtime == null || Boolean.valueOf(runtime)) {
            random = new SecureRandom();
        } else {
            random = new Random(7);
        }
    }
}
