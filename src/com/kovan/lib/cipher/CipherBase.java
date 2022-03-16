package com.kovan.lib.cipher;

abstract class CipherBase {
    public CipherBase() {
    }

    public abstract void Init(String var1) throws Exception;

    public abstract String encryptBase64AES(String var1, String var2, byte[] var3, String var4) throws Exception;

    public abstract String decryptBase64AES(String var1, String var2, byte[] var3, String var4) throws Exception;
}
