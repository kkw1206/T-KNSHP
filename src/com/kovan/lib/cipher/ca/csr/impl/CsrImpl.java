package com.kovan.lib.cipher.ca.csr.impl;

import com.kovan.lib.cipher.ca.CaException;
import com.kovan.lib.cipher.ca.certificate.DistinguishedName;
import com.kovan.lib.cipher.ca.certificate.impl.BcX500NameDnImpl;
import com.kovan.lib.cipher.ca.csr.CSR;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import java.security.PublicKey;

public class CsrImpl implements CSR {
    private final DistinguishedName dn;
    private final PublicKey publicKey;

    public CsrImpl(PKCS10CertificationRequest request) {
        this.dn = new BcX500NameDnImpl(request.getSubject());

        try {
            this.publicKey = (new JcaPEMKeyConverter()).getPublicKey(request.getSubjectPublicKeyInfo());
        } catch (PEMException var3) {
            throw new CaException(var3);
        }
    }

    public DistinguishedName getSubject() {
        return this.dn;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}
