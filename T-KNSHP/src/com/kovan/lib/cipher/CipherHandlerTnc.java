package com.kovan.lib.cipher;

import com.kovan.lib.cipher.impl.AesCipherImpl;

public class CipherHandlerTnc extends CipherBase {
    public CipherHandlerTnc() {
    }

    public void Init(String domain) throws Exception {
    }

    public String encryptBase64AES(String plainText, String sessionKey, byte[] iv, String charset) throws Exception {
        String rtnStr = null;
        AesCipherImpl aesCipher = new AesCipherImpl();
        rtnStr = aesCipher.encryptBase64(plainText, sessionKey, iv, charset);
        return rtnStr;
    }

    public String decryptBase64AES(String cipherText, String sessionKey, byte[] iv, String charset) throws Exception {
        String rtnStr = null;
        AesCipherImpl aesCipher = new AesCipherImpl();
        rtnStr = aesCipher.decryptBase64(cipherText, sessionKey, iv, charset);
        return rtnStr;
    }
}
