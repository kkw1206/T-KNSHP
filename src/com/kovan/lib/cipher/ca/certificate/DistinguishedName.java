package com.kovan.lib.cipher.ca.certificate;

import org.bouncycastle.asn1.x500.X500Name;

import javax.security.auth.x500.X500Principal;

public interface DistinguishedName {
    X500Name getX500Name();

    X500Principal getX500Principal();

    byte[] getEncoded();

    String getName();
}
