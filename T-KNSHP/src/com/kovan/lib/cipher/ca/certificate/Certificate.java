package com.kovan.lib.cipher.ca.certificate;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

public interface Certificate {
    X509Certificate getX509Certificate();

    String print();

    void save(File var1);

    void save(String var1);

    PublicKey getPublicKey();

    String printPublicKey();

    void savePublicKey(File var1);

    void savePublicKey(String var1);

    CertificateWithPrivateKey attachPrivateKey(PrivateKey var1);
}
