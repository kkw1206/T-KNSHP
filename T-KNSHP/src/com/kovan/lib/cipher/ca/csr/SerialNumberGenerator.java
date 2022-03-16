package com.kovan.lib.cipher.ca.csr;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SerialNumberGenerator {
    private static final int DEFAULT_SERIAL_LENGTH = 128;
    private final SecureRandom random;
    private final int length;
    public static SerialNumberGenerator instance = new SerialNumberGenerator();

    public SerialNumberGenerator() {
        this(new SecureRandom(), 128);
    }

    public SerialNumberGenerator(SecureRandom random, int length) {
        this.random = random;
        this.length = length;
    }

    public BigInteger generateRandomSerialNumber() {
        return new BigInteger(this.length, this.random);
    }
}