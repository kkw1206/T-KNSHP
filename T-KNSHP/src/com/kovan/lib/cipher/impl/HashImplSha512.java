package com.kovan.lib.cipher.impl;


import com.kovan.lib.cipher.Hash;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashImplSha512 implements Hash {
    private String defaultCharset = "UTF-8";
    private String algorithm = "SHA-512";

    public HashImplSha512() {
    }

    public String getSha512(String input, String key) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
        if (input != null && key != null && input.length() >= 1 && key.length() >= 1) {
            MessageDigest sh = MessageDigest.getInstance(this.algorithm);
            sh.update((key + input).getBytes(this.defaultCharset));
            byte[] encBytes = sh.digest();
            String b64EncString = DatatypeConverter.printBase64Binary(encBytes);
            return b64EncString;
        } else {
            return null;
        }
    }

    public String getSha512NoSalt(byte[] input, String key) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
        if (input != null && key != null && input.length >= 1 && key.length() >= 1) {
            MessageDigest sh = MessageDigest.getInstance(this.algorithm);
            sh.update(input);
            byte[] encBytes = sh.digest();
            String b64EncString = DatatypeConverter.printBase64Binary(encBytes);
            return b64EncString;
        } else {
            return null;
        }
    }

    public boolean verifyHash(String data, String key, String hash) {
        String checkHash;
        try {
            checkHash = this.getSha256(data, key);
        } catch (InvalidKeyException var6) {
            return false;
        } catch (NoSuchAlgorithmException var7) {
            return false;
        } catch (IllegalStateException var8) {
            return false;
        } catch (UnsupportedEncodingException var9) {
            return false;
        }

        return hash.equals(checkHash);
    }

    public String getDefaultCharset() {
        return this.defaultCharset;
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getSha256(String input, String key) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
        return null;
    }
}
