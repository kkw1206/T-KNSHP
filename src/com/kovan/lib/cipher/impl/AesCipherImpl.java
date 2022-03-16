package com.kovan.lib.cipher.impl;

import com.kovan.lib.cipher.SymmetricCipher;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.spec.AlgorithmParameterSpec;

public class AesCipherImpl implements SymmetricCipher {
    private String defaultCharset = "UTF-8";
    private final String algorithm = "AES";
    private final String transformation = "AES/CBC/PKCS5Padding";
    private byte[] ivBytes = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public AesCipherImpl() {
    }

    public String encryptBase64(String plainText, String keyBase64String) throws Exception {
        return this.encryptBase64(plainText, this.defaultCharset, keyBase64String);
    }

    public String encryptBase64(String plainText, String keyBase64String, byte[] ivBytes, String charset) throws Exception {
        return this.encryptBase64(plainText, charset, keyBase64String, ivBytes);
    }

    public String encryptBase64(String plainText, String charset, String keyBase64String) throws Exception {
        if (plainText != null && keyBase64String != null && plainText.length() >= 1 && keyBase64String.length() >= 1) {
            String b64EncString = null;
            byte[] keyBytes = null;
            keyBytes = DatatypeConverter.parseBase64Binary(keyBase64String);
            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            b64EncString = this.encryptBase64(plainText, charset, (SecretKey)key);
            return b64EncString;
        } else {
            return null;
        }
    }

    public String encryptBase64(String plainText, String charset, String keyBase64String, byte[] ivBytes) throws Exception {
        if (plainText != null && keyBase64String != null && plainText.length() >= 1 && keyBase64String.length() >= 1) {
            String b64EncString = null;
            byte[] keyBytes = null;
            keyBytes = DatatypeConverter.parseBase64Binary(keyBase64String);
            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            b64EncString = this.encryptBase64(plainText, charset, (SecretKey)key, (byte[])ivBytes);
            return b64EncString;
        } else {
            return null;
        }
    }

    public String encryptBase64(String plainText, SecretKey key) throws Exception {
        return this.encryptBase64(plainText, this.defaultCharset, key);
    }

    public String encryptBase64(byte[] plainBytes, SecretKey key) throws Exception {
        if (plainBytes != null && key != null && plainBytes.length >= 1) {
            AlgorithmParameterSpec iv = new IvParameterSpec(this.ivBytes);
            String b64EncString = null;
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(1, key, iv);
            byte[] encBytes = c.doFinal(plainBytes);
            b64EncString = DatatypeConverter.printBase64Binary(encBytes);
            return b64EncString;
        } else {
            return null;
        }
    }

    public String encryptBase64(String plainText, String charset, SecretKey key) throws Exception {
        if (plainText != null && key != null && plainText.length() >= 1 && key != null) {
            AlgorithmParameterSpec iv = new IvParameterSpec(this.ivBytes);
            String b64EncString = null;
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(1, key, iv);
            byte[] inBytes = plainText.getBytes(this.defaultCharset);
            byte[] encBytes = c.doFinal(inBytes);
            b64EncString = DatatypeConverter.printBase64Binary(encBytes);
            return b64EncString;
        } else {
            return null;
        }
    }

    public String encryptBase64(String plainText, String charset, SecretKey key, byte[] ivbyte) throws Exception {
        if (plainText != null && key != null && plainText.length() >= 1 && key != null) {
            AlgorithmParameterSpec iv = new IvParameterSpec(ivbyte);
            String b64EncString = null;
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(1, key, iv);
            byte[] inBytes = plainText.getBytes(this.defaultCharset);
            byte[] encBytes = c.doFinal(inBytes);
            b64EncString = DatatypeConverter.printBase64Binary(encBytes);
            return b64EncString;
        } else {
            return null;
        }
    }

    public String decryptBase64(String cipherText, SecretKey key) throws Exception {
        return this.decryptBase64(cipherText, this.defaultCharset, key);
    }

    public String decryptBase64(String cipherText, String keyBase64String) throws Exception {
        return this.decryptBase64(cipherText, this.defaultCharset, keyBase64String);
    }

    public String decryptBase64(String cipherText, String keyBase64String, byte[] ivBytes, String charset) throws Exception {
        return this.decryptBase64(cipherText, charset, keyBase64String, ivBytes);
    }

    public String decryptBase64(String cipherText, String charset, String keyBase64String) throws Exception {
        if (cipherText != null && keyBase64String != null && cipherText.length() >= 1 && keyBase64String.length() >= 1) {
            String decString = null;
            byte[] keyBytes = null;
            keyBytes = DatatypeConverter.parseBase64Binary(keyBase64String);
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            decString = this.decryptBase64(cipherText, charset, (SecretKey)key);
            return decString;
        } else {
            return null;
        }
    }

    public String decryptBase64(String cipherText, String charset, String keyBase64String, byte[] ivBytes) throws Exception {
        if (cipherText != null && keyBase64String != null && cipherText.length() >= 1 && keyBase64String.length() >= 1) {
            String decString = null;
            byte[] keyBytes = null;
            keyBytes = DatatypeConverter.parseBase64Binary(keyBase64String);
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            decString = this.decryptBase64(cipherText, charset, (SecretKey)key, (byte[])ivBytes);
            return decString;
        } else {
            return null;
        }
    }

    public String decryptBase64(String cipherText, String charset, SecretKey key) throws Exception {
        if (cipherText != null && key != null && cipherText.length() >= 1 && key != null) {
            String decString = null;
            byte[] decBytes = this.decryptBytesBase64(cipherText, key);
            decString = new String(decBytes, charset);
            return decString;
        } else {
            return null;
        }
    }

    public String decryptBase64(String cipherText, String charset, SecretKey key, byte[] ivBytes) throws Exception {
        if (cipherText != null && key != null && cipherText.length() >= 1 && key != null) {
            String decString = null;
            byte[] decBytes = this.decryptBytesBase64(cipherText, key, ivBytes);
            decString = new String(decBytes, charset);
            return decString;
        } else {
            return null;
        }
    }

    public byte[] decryptBytesBase64(String cipherText, SecretKey key) throws Exception {
        if (cipherText != null && key != null && cipherText.length() >= 1 && key != null) {
            AlgorithmParameterSpec iv = new IvParameterSpec(this.ivBytes);
            byte[] decBytes = null;
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(2, key, iv);
            byte[] encBytes = DatatypeConverter.parseBase64Binary(cipherText);
            decBytes = c.doFinal(encBytes);
            return decBytes;
        } else {
            return null;
        }
    }

    public byte[] decryptBytesBase64(String cipherText, SecretKey key, byte[] ivbyte) throws Exception {
        if (cipherText != null && key != null && cipherText.length() >= 1 && key != null) {
            AlgorithmParameterSpec iv = new IvParameterSpec(ivbyte);
            byte[] decBytes = null;
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(2, key, iv);
            byte[] encBytes = DatatypeConverter.parseBase64Binary(cipherText);
            decBytes = c.doFinal(encBytes);
            return decBytes;
        } else {
            return null;
        }
    }

    public String getDefaultCharset() {
        return this.defaultCharset;
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    public String getAlgorithm() {
        return "AES";
    }

    public String getTransformation() {
        return "AES/CBC/PKCS5Padding";
    }
}
