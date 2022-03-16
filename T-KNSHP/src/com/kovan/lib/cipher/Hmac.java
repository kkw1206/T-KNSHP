package com.kovan.lib.cipher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface Hmac {
    String getHmac(String var1, String var2) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException;

    String getHmac(String var1, String var2, String var3) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException;

    boolean verifyHmac(String var1, String var2, String var3);
}
