package com.kovan.lib.cipher.ca.csr;

import com.kovan.lib.cipher.ca.certificate.DistinguishedName;

import java.security.PublicKey;

public interface CSR {
    DistinguishedName getSubject();

    PublicKey getPublicKey();
}