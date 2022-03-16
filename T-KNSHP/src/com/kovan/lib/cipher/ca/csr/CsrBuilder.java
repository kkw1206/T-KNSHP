package com.kovan.lib.cipher.ca.csr;

import com.kovan.lib.cipher.ca.certificate.DistinguishedName;

public interface CsrBuilder {
    CsrWithPrivateKey generateRequest(String var1, DistinguishedName var2);
}
