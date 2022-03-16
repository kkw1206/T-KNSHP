package com.kovan.lib.cipher.ca.csr;

import com.kovan.lib.cipher.ca.certificate.Certificate;
import com.kovan.lib.cipher.ca.certificate.ext.CertExtension;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

import java.util.Date;

public interface SignerWithSerial extends Signer {
    Certificate sign(String var1);

    SignerWithSerial setNotBefore(Date var1);

    SignerWithSerial setNotAfter(Date var1);

    SignerWithSerial validDuringYears(int var1);

    SignerWithSerial addExtension(CertExtension var1);

    SignerWithSerial addExtension(ASN1ObjectIdentifier var1, boolean var2, ASN1Encodable var3);
}
