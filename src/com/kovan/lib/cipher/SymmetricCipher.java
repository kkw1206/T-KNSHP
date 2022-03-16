package com.kovan.lib.cipher;

import javax.crypto.SecretKey;

public interface SymmetricCipher {
    String encryptBase64(String var1, String var2, String var3) throws Exception;

    String encryptBase64(String var1, String var2, String var3, byte[] var4) throws Exception;

    String encryptBase64(String var1, String var2) throws Exception;

    String encryptBase64(String var1, String var2, byte[] var3, String var4) throws Exception;

    String encryptBase64(byte[] var1, SecretKey var2) throws Exception;

    String decryptBase64(String var1, String var2, String var3) throws Exception;

    String decryptBase64(String var1, String var2, String var3, byte[] var4) throws Exception;

    String decryptBase64(String var1, String var2) throws Exception;

    String decryptBase64(String var1, String var2, byte[] var3, String var4) throws Exception;

    byte[] decryptBytesBase64(String var1, SecretKey var2) throws Exception;
}
