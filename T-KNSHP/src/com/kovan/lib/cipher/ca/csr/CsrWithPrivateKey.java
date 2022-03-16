package com.kovan.lib.cipher.ca.csr;

import java.security.PrivateKey;

public interface CsrWithPrivateKey extends CSR {
    PrivateKey getPrivateKey();
}
