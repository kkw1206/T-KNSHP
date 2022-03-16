package com.kovan.lib.cipher.ca.certificate.ext;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

public class CertExtension {
    private final ASN1ObjectIdentifier oid;
    private final boolean isCritical;
    private final ASN1Encodable value;

    public CertExtension(ASN1ObjectIdentifier oid, boolean isCritical, ASN1Encodable value) {
        this.oid = oid;
        this.isCritical = isCritical;
        this.value = value;
    }

    public ASN1ObjectIdentifier getOid() {
        return this.oid;
    }

    public boolean isCritical() {
        return this.isCritical;
    }

    public ASN1Encodable getValue() {
        return this.value;
    }

    public String toString() {
        return "Extension [" + this.oid + "=" + this.value + "]";
    }
}
