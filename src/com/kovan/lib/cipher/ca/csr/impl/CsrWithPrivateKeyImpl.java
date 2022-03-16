package com.kovan.lib.cipher.ca.csr.impl;

import com.kovan.lib.cipher.ca.csr.CsrWithPrivateKey;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import java.security.PrivateKey;

class CsrWithPrivateKeyImpl extends CsrImpl implements CsrWithPrivateKey {
    private final PrivateKey privateKey;

    CsrWithPrivateKeyImpl(PKCS10CertificationRequest request, PrivateKey privateKey) {
        super(request);
        this.privateKey = privateKey;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
}
