package com.kovan.lib.cipher.impl;


import com.kovan.lib.cipher.AsymmetricCipher;
import com.kovan.lib.cipher.provider.DefaultSecurityProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class RsaCipherImpl implements AsymmetricCipher {
    final String transformation = "RSA/None/NoPadding";
    private String defaultCharset = "UTF-8";

    public RsaCipherImpl() {
    }

    public String encryptBase64(String plainText, Key key) {
        return this.encryptBase64(plainText, this.defaultCharset, key);
    }

    public String encryptBase64(String plainText, String charset, Key key) {
        if (plainText != null && key != null && plainText.length() >= 1 && key.getAlgorithm() != null) {
            try {
                byte[] plainBytes = plainText.getBytes(charset);
                byte[] cipherBytes = this.encrypt(plainBytes, key);
                String cipherText = DatatypeConverter.printBase64Binary(cipherBytes);
                return cipherText;
            } catch (NoSuchAlgorithmException var7) {
                return null;
            } catch (NoSuchProviderException var8) {
                return null;
            } catch (NoSuchPaddingException var9) {
                return null;
            } catch (InvalidKeyException var10) {
                return null;
            } catch (IllegalBlockSizeException var11) {
                return null;
            } catch (BadPaddingException var12) {
                return null;
            } catch (UnsupportedEncodingException var13) {
                return null;
            }
        } else {
            return null;
        }
    }

    public String encryptBase64(String plainText, String charset, Key key, String transformations) throws Exception {
        if (plainText != null && key != null && plainText.length() >= 1 && key.getAlgorithm() != null) {
            byte[] plainBytes = plainText.getBytes(charset);
            byte[] cipherBytes = this.encryptRsa(plainBytes, key, transformations);
            String cipherText = DatatypeConverter.printBase64Binary(cipherBytes);
            return cipherText;
        } else {
            return null;
        }
    }

    public String encryptBase64(byte[] plainBytes, Key key) {
        if (plainBytes != null && key != null && plainBytes.length >= 1 && key.getAlgorithm() != null) {
            try {
                byte[] cipherBytes = this.encrypt(plainBytes, key);
                String cipherText = DatatypeConverter.printBase64Binary(cipherBytes);
                return cipherText;
            } catch (NoSuchAlgorithmException var5) {
                return null;
            } catch (NoSuchProviderException var6) {
                return null;
            } catch (NoSuchPaddingException var7) {
                return null;
            } catch (InvalidKeyException var8) {
                return null;
            } catch (IllegalBlockSizeException var9) {
                return null;
            } catch (BadPaddingException var10) {
                return null;
            }
        } else {
            return null;
        }
    }

    public byte[] encrypt(String plainText, String charset, Key key) {
        if (plainText != null && key != null && plainText.length() >= 1 && key.getAlgorithm() != null) {
            try {
                byte[] plainBytes = plainText.getBytes(charset);
                byte[] cipherBytes = this.encrypt(plainBytes, key);
                String cipherText = DatatypeConverter.printBase64Binary(cipherBytes);
                return cipherBytes;
            } catch (NoSuchAlgorithmException var7) {
                return null;
            } catch (NoSuchProviderException var8) {
                return null;
            } catch (NoSuchPaddingException var9) {
                return null;
            } catch (InvalidKeyException var10) {
                return null;
            } catch (IllegalBlockSizeException var11) {
                return null;
            } catch (BadPaddingException var12) {
                return null;
            } catch (UnsupportedEncodingException var13) {
                return null;
            }
        } else {
            return null;
        }
    }

    public byte[] encrypt(byte[] plainBytes, Key key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return this.encryptRsa(plainBytes, key);
    }

    public byte[] encryptRsa(byte[] plainBytes, Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (plainBytes != null && key != null && plainBytes.length >= 1 && key.getAlgorithm() != null) {
            Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", DefaultSecurityProvider.getProvider());
            cipher.init(1, key);
            byte[] cipherText = cipher.doFinal(plainBytes);
            return cipherText;
        } else {
            return null;
        }
    }

    public byte[] encryptRsa(byte[] plainBytes, Key key, String transformation) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (plainBytes != null && key != null && plainBytes.length >= 1 && key.getAlgorithm() != null) {
            Cipher cipher = Cipher.getInstance(transformation, DefaultSecurityProvider.getProvider());
            cipher.init(1, key);
            byte[] cipherText = cipher.doFinal(plainBytes);
            return cipherText;
        } else {
            return null;
        }
    }

    public String decryptBase64(String cipherText, Key key) {
        return this.decryptBase64(cipherText, this.defaultCharset, key);
    }

    public String decryptBase64(String cipherText, String charset, Key key) {
        if (cipherText != null && key != null && cipherText.length() >= 1 && key.getAlgorithm() != null) {
            try {
                byte[] cipherBytes = DatatypeConverter.parseBase64Binary(cipherText);
                byte[] plainBytes = this.decryptRsa(cipherBytes, key);
                String plainText = new String(plainBytes, charset);
                return plainText;
            } catch (NoSuchAlgorithmException var7) {
                return null;
            } catch (NoSuchProviderException var8) {
                return null;
            } catch (NoSuchPaddingException var9) {
                return null;
            } catch (InvalidKeyException var10) {
                return null;
            } catch (IllegalBlockSizeException var11) {
                return null;
            } catch (BadPaddingException var12) {
                return null;
            } catch (UnsupportedEncodingException var13) {
                return null;
            }
        } else {
            return null;
        }
    }

    public String decryptBase64(String cipherText, String charset, Key key, String transformation) {
        if (cipherText != null && key != null && cipherText.length() >= 1 && key.getAlgorithm() != null) {
            try {
                byte[] cipherBytes = DatatypeConverter.parseBase64Binary(cipherText);
                byte[] plainBytes = this.decryptRsa(cipherBytes, key, transformation);
                String plainText = new String(plainBytes, charset);
                return plainText;
            } catch (NoSuchAlgorithmException var8) {
                return null;
            } catch (NoSuchProviderException var9) {
                return null;
            } catch (NoSuchPaddingException var10) {
                return null;
            } catch (InvalidKeyException var11) {
                return null;
            } catch (IllegalBlockSizeException var12) {
                return null;
            } catch (BadPaddingException var13) {
                return null;
            } catch (UnsupportedEncodingException var14) {
                return null;
            }
        } else {
            return null;
        }
    }

    public byte[] decrypt(byte[] cipherBytes, Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (cipherBytes != null && key != null && cipherBytes.length >= 1 && key.getAlgorithm() != null) {
            Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", DefaultSecurityProvider.getProvider());
            cipher.init(2, key);
            byte[] plainText = cipher.doFinal(cipherBytes);
            return plainText;
        } else {
            return null;
        }
    }

    public byte[] decryptRsa(byte[] cipherBytes, Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (cipherBytes != null && key != null && cipherBytes.length >= 1 && key.getAlgorithm() != null) {
            Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", DefaultSecurityProvider.getProvider());
            cipher.init(2, key);
            byte[] plainText = cipher.doFinal(cipherBytes);
            return plainText;
        } else {
            return null;
        }
    }

    public byte[] decryptRsa(byte[] cipherBytes, Key key, String transformation) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (cipherBytes != null && key != null && cipherBytes.length >= 1 && key.getAlgorithm() != null) {
            Cipher cipher = Cipher.getInstance(transformation, DefaultSecurityProvider.getProvider());
            cipher.init(2, key);
            byte[] plainText = cipher.doFinal(cipherBytes);
            return plainText;
        } else {
            return null;
        }
    }

    public byte[] decryptBytesBase64(String cipherText, Key key) {
        if (cipherText != null && key != null && cipherText.length() >= 1 && key.getAlgorithm() != null) {
            try {
                byte[] cipherBytes = DatatypeConverter.parseBase64Binary(cipherText);
                byte[] plainBytes = this.decryptRsa(cipherBytes, key);
                return plainBytes;
            } catch (NoSuchAlgorithmException var5) {
                return null;
            } catch (NoSuchProviderException var6) {
                return null;
            } catch (NoSuchPaddingException var7) {
                return null;
            } catch (InvalidKeyException var8) {
                return null;
            } catch (IllegalBlockSizeException var9) {
                return null;
            } catch (BadPaddingException var10) {
                return null;
            }
        } else {
            return null;
        }
    }

    public String getTransformation() {
        return "RSA/None/NoPadding";
    }

    public String getDefaultCharset() {
        return this.defaultCharset;
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }
}
