package com.kovan.lib.cipher.ca.certificate.impl;


import com.kovan.lib.cipher.ca.CaException;
import com.kovan.lib.cipher.ca.certificate.DistinguishedName;
import org.bouncycastle.asn1.x500.X500Name;

import javax.security.auth.x500.X500Principal;
import java.io.IOException;

public class BcX500NameDnImpl implements DistinguishedName {
    private final X500Name x500Name;

    public BcX500NameDnImpl(X500Name name) {
        this.x500Name = name;
    }

    public BcX500NameDnImpl(String name) {
        this.x500Name = new X500Name(name);
    }

    public BcX500NameDnImpl(X500Principal principal) {
        this.x500Name = X500Name.getInstance(principal.getEncoded());
    }

    public X500Name getX500Name() {
        return this.x500Name;
    }

    public X500Principal getX500Principal() {
        try {
            return new X500Principal(this.x500Name.getEncoded());
        } catch (IOException var2) {
            throw new CaException(var2);
        }
    }

    public byte[] getEncoded() {
        try {
            return this.x500Name.getEncoded();
        } catch (IOException var2) {
            throw new CaException(var2);
        }
    }

    public String getName() {
        return this.x500Name.toString();
    }

    public String toString() {
        return this.getName();
    }
}
