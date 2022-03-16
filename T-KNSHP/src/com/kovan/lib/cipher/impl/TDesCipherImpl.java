package com.kovan.lib.cipher.impl;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;
import java.security.spec.KeySpec;

public class TDesCipherImpl {
    private String defaultCharset = "UTF-8";
    private final String algorithm = "DESede";
    private final String transformation = "DESede/CBC/PKCS5Padding";
    private byte[] ivBytes = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public TDesCipherImpl() {
    }

    public String encryptBase64(String plainText, String charset, String keyBase64String, byte[] ivBytes) throws Exception {
        if (plainText != null && keyBase64String != null && plainText.length() >= 1 && keyBase64String.length() >= 1) {
            String b64EncString = null;
            KeySpec keySpec = new DESedeKeySpec(keyBase64String.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key = keyFactory.generateSecret(keySpec);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            byte[] bytMsg = plainText.getBytes(charset);
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(1, key, ivSpec);
            byte[] encBytes = cipher.doFinal(bytMsg);
            b64EncString = DatatypeConverter.printBase64Binary(encBytes);
            return b64EncString;
        } else {
            return null;
        }
    }

    public String decryptBase64(String cipherText, String charset, String keyBase64String, byte[] ivBytes) throws Exception {
        String b64EncString = null;
        KeySpec keySpec = new DESedeKeySpec(keyBase64String.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey key = keyFactory.generateSecret(keySpec);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        byte[] bytEncMsg = DatatypeConverter.parseBase64Binary(cipherText);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(2, key, ivSpec);
        byte[] decMsg = cipher.doFinal(bytEncMsg);
        String sDecRes = new String(decMsg, charset);
        return sDecRes;
    }

    public String getDefaultCharset() {
        return this.defaultCharset;
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    public String getAlgorithm() {
        return "DESede";
    }

    public String getTransformation() {
        return "DESede/CBC/PKCS5Padding";
    }
}
