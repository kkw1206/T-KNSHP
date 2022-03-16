package com.kovan.lib.cipher.provider;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class DefaultSecurityProvider {
    private static final String provider = "BC";

    public DefaultSecurityProvider() {
    }

    public static String getProvider() {
        return "BC";
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
}
