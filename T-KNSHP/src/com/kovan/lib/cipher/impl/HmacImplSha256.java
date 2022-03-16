package com.kovan.lib.cipher.impl;


import com.kovan.lib.cipher.Hmac;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacImplSha256 implements Hmac {
    private String defaultCharset = "UTF-8";
    private String algorithm = "HmacSHA256";

    public HmacImplSha256() {
    }

    public String getHmac(String input, String key) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
        if (input != null && key != null && input.length() >= 1 && key.length() >= 1) {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(this.defaultCharset), this.algorithm);
            Mac mac = Mac.getInstance(this.algorithm);
            mac.init(keySpec);
            byte[] inBytes = input.getBytes(this.defaultCharset);
            byte[] encBytes = mac.doFinal(inBytes);
            String b64EncString = DatatypeConverter.printBase64Binary(encBytes);
            return b64EncString;
        } else {
            return null;
        }
    }

    public String getHmac(String input, String key, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
        if (input != null && key != null && input.length() >= 1 && key.length() >= 1) {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(this.defaultCharset), algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(keySpec);
            byte[] inBytes = input.getBytes(this.defaultCharset);
            byte[] encBytes = mac.doFinal(inBytes);
            String b64EncString = DatatypeConverter.printBase64Binary(encBytes);
            return b64EncString;
        } else {
            return null;
        }
    }

    public boolean verifyHmac(String data, String key, String hmac) {
        String checkHash;
        try {
            checkHash = this.getHmac(data, key);
        } catch (InvalidKeyException var6) {
            return false;
        } catch (NoSuchAlgorithmException var7) {
            return false;
        } catch (IllegalStateException var8) {
            return false;
        } catch (UnsupportedEncodingException var9) {
            return false;
        }

        return hmac.equals(checkHash);
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
}
