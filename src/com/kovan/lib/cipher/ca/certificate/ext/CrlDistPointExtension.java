package com.kovan.lib.cipher.ca.certificate.ext;

import org.bouncycastle.asn1.x509.*;

public class CrlDistPointExtension extends CertExtension{
    CrlDistPointExtension(DistributionPoint... points) {
        super(Extension.cRLDistributionPoints, false, new CRLDistPoint(points));
    }

    public static CrlDistPointExtension create(String uri) {
        return create(NameType.URI, uri);
    }

    public static CrlDistPointExtension create(NameType type, String name) {
        return create(type, name, (NameType)null, (String)null, (ReasonFlags)null);
    }

    public static CrlDistPointExtension create(NameType distribPointNameType, String distribPointName, NameType crlIssuerNameType, String crlIssuer, ReasonFlags reasons) {
        DistributionPointName dp = new DistributionPointName(distribPointNameType.generalNames(distribPointName));
        GeneralNames crl;
        if (crlIssuerNameType != null && crlIssuer != null) {
            crl = crlIssuerNameType.generalNames(crlIssuer);
        } else {
            crl = null;
        }

        return create(dp, reasons, crl);
    }

    public static CrlDistPointExtension create(DistributionPointName distributionPoint, ReasonFlags reasons, GeneralNames cRLIssuer) {
        DistributionPoint p = new DistributionPoint(distributionPoint, reasons, cRLIssuer);
        return create(p);
    }

    public static CrlDistPointExtension create(DistributionPoint... points) {
        return new CrlDistPointExtension(points);
    }
}
