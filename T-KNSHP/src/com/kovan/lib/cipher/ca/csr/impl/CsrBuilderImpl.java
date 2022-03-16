package com.kovan.lib.cipher.ca.csr.impl;

import com.kovan.lib.cipher.ca.CaException;
import com.kovan.lib.cipher.ca.certificate.DistinguishedName;
import com.kovan.lib.cipher.ca.csr.CsrBuilder;
import com.kovan.lib.cipher.ca.csr.CsrWithPrivateKey;
import com.kovan.lib.cipher.key.RsaKeysUtil;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class CsrBuilderImpl implements CsrBuilder {
    public CsrBuilderImpl() {
    }

    public CsrWithPrivateKey generateRequest(String signatureAlgorithm, DistinguishedName dn) {
        KeyPair pair = RsaKeysUtil.generateKeyPair();

        try {
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            X500Name x500Name = dn.getX500Name();
            ContentSigner signGen = (new JcaContentSignerBuilder(signatureAlgorithm)).build(privateKey);
            PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(x500Name, publicKey);
            PKCS10CertificationRequest csr = builder.build(signGen);
            return new CsrWithPrivateKeyImpl(csr, privateKey);
        } catch (OperatorCreationException var10) {
            throw new CaException(var10);
        }
    }
}