package com.kovan.lib.cipher.ca.certificate.ext;

import org.bouncycastle.asn1.x509.Extension;

public class KeyUsageExtension extends CertExtension {
    KeyUsageExtension(int keyUsages) {
        super(Extension.keyUsage, false, new org.bouncycastle.asn1.x509.KeyUsage(keyUsages));
    }

    KeyUsageExtension(KeyUsage... usages) {
        this(getUsages(usages));
    }

    public static KeyUsageExtension create(KeyUsage... usages) {
        return new KeyUsageExtension(usages);
    }

    private static int getUsages(KeyUsage[] usages) {
        int u = 0;
        KeyUsage[] var2 = usages;
        int var3 = usages.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            KeyUsage ku = var2[var4];
            u |= ku.keyUsage;
        }

        return u;
    }

    public static enum KeyUsage {
        DIGITAL_SIGNATURE(128),
        NON_REPUDIATION(64),
        KEY_ENCIPHERMENT(32),
        DATA_ENCIPHERMENT(16),
        KEY_AGREEMENT(8),
        KEY_CERT_SIGN(4),
        CRL_SIGN(2),
        ENCIPHER_ONLY(1),
        DECIPHER_ONLY(1);

        private final int keyUsage;

        private KeyUsage(int keyUsage) {
            this.keyUsage = keyUsage;
        }
    }
}
