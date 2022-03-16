package com.kovan.lib.cipher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface Hash {
    String getSha256(String var1, String var2) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException;

    boolean verifyHash(String var1, String var2, String var3);

    String getSha512(String var1, String var2) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException;
}
