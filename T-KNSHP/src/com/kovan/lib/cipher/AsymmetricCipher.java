package com.kovan.lib.cipher;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface AsymmetricCipher {
    String encryptBase64(String var1, Key var2);

    String encryptBase64(String var1, String var2, Key var3);

    String encryptBase64(String var1, String var2, Key var3, String var4) throws Exception;

    String encryptBase64(byte[] var1, Key var2);

    byte[] encrypt(byte[] var1, Key var2) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;

    String decryptBase64(String var1, String var2, Key var3);

    String decryptBase64(String var1, String var2, Key var3, String var4);

    String decryptBase64(String var1, Key var2);

    byte[] decrypt(byte[] var1, Key var2) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;

    byte[] decryptBytesBase64(String var1, Key var2);
}
