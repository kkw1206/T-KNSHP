package com.kovan.lib.cipher.ca.certificate.ext;

import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyPurposeId;

public class ExtKeyUsageExtension extends CertExtension {
    ExtKeyUsageExtension(ExtendedKeyUsage extendedKeyUsage) {
        super(Extension.extendedKeyUsage, false, extendedKeyUsage);
    }

    ExtKeyUsageExtension(KeyPurposeId usage) {
        this(new ExtendedKeyUsage(usage));
    }

    ExtKeyUsageExtension(KeyPurposeId[] usages) {
        this(new ExtendedKeyUsage(usages));
    }

    public static ExtKeyUsageExtension create(KeyPurposeId usage) {
        return new ExtKeyUsageExtension(usage);
    }

    public static ExtKeyUsageExtension create(KeyPurposeId... usages) {
        return new ExtKeyUsageExtension(usages);
    }
}
