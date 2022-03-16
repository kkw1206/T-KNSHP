package com.kovan.lib.cipher.ca.certificate;

import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;

public interface CertificateWithPrivateKey extends Certificate {
    KeyStore addToKeystore(KeyStore var1, String var2);

    KeyStore saveInPkcs12Keystore(String var1);

    void exportPkcs12(String var1, char[] var2, String var3);

    void exportPkcs12(File var1, char[] var2, String var3);

    PrivateKey getPrivateKey();

    String printPrivateKey();

    void savePrivateKey(File var1);

    void savePrivateKey(String var1);
}
