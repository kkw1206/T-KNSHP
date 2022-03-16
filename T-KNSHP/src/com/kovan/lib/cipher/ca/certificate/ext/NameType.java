package com.kovan.lib.cipher.ca.certificate.ext;

import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;

public enum NameType {
    OTHER_NAME(0),
    RFC_822_NAME(1),
    DNS_NAME(2),
    X400_NAME(3),
    DIRECTORY_NAME(4),
    EDI_PARTY_NAME(5),
    URI(6),
    IP_ADDRESS(7),
    REGISTERED_ID(8);

    private final int id;

    private NameType(int id) {
        this.id = id;
    }

    public GeneralName generalName(String name) {
        return new GeneralName(this.id, name);
    }

    public GeneralNames generalNames(String name) {
        return new GeneralNames(this.generalName(name));
    }
}
