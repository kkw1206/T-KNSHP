package com.kovan.lib.cipher.cert;


import com.kovan.lib.cipher.file.RsaKeyFileUtil;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public final class CertUtil {
    public CertUtil() {
    }

    public static boolean saveCertificate(X509Certificate cert, String fileName) {
        boolean result = false;
        FileWriter fileWriter = null;
        JcaPEMWriter jcaPEMWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            jcaPEMWriter = new JcaPEMWriter(fileWriter);
            jcaPEMWriter.writeObject(cert);
            result = true;
        } catch (IOException var9) {
            result = false;
        } finally {
            IOUtils.closeQuietly(jcaPEMWriter);
            IOUtils.closeQuietly(fileWriter);
        }

        return result;
    }

    public static X509Certificate loadCertificateFromPemFile(String certPemFileName) {
        try {
            InputStream inputStream = RsaKeyFileUtil.readFileFromClassPathOrPath(certPemFileName);

            X509Certificate var5;
            try {
                CertificateFactory fact = CertificateFactory.getInstance("X.509");
                X509Certificate cer = (X509Certificate)fact.generateCertificate(inputStream);
                PublicKey key = cer.getPublicKey();
                var5 = cer;
            } finally {
                IOUtils.closeQuietly(inputStream);
            }

            return var5;
        } catch (Exception var10) {
            throw new RuntimeException("Exception while loading PEM file from " + certPemFileName, var10);
        }
    }
}
