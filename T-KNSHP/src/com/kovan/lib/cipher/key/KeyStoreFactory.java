package com.kovan.lib.cipher.key;


import com.kovan.lib.cipher.file.RsaKeyFileUtil;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.bc.BcX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.IPAddress;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class KeyStoreFactory {
    private static final String PROVIDER_NAME = "BC";
    private static final String SIGNATURE_ALGORITHM = "SHA256WithRSAEncryption";
    private static final String KEY_GENERATION_ALGORITHM = "RSA";
    private static final boolean REGENERATE_FRESH_CA_CERTIFICATE = false;
    private static final int ROOT_KEYSIZE = 2048;
    private static final int FAKE_KEYSIZE = 1024;
    private static final Date NOT_BEFORE;
    private static final Date NOT_AFTER;

    public KeyStoreFactory() {
    }

    public static KeyPair generateKeyPair(int keySize) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(keySize, new SecureRandom());
        return generator.generateKeyPair();
    }

    private static SubjectKeyIdentifier createSubjectKeyIdentifier(Key key) throws IOException {
        ASN1InputStream is = null;

        SubjectKeyIdentifier var4;
        try {
            is = new ASN1InputStream(new ByteArrayInputStream(key.getEncoded()));
            ASN1Sequence seq = (ASN1Sequence)is.readObject();
            SubjectPublicKeyInfo info = SubjectPublicKeyInfo.getInstance(seq);
            var4 = (new BcX509ExtensionUtils()).createSubjectKeyIdentifier(info);
        } finally {
            IOUtils.closeQuietly(is);
        }

        return var4;
    }

    private static X509Certificate signCertificate(X509v3CertificateBuilder certificateBuilder, PrivateKey signedWithPrivateKey) throws OperatorCreationException, CertificateException {
        ContentSigner signer = (new JcaContentSignerBuilder("SHA256WithRSAEncryption")).setProvider("BC").build(signedWithPrivateKey);
        return (new JcaX509CertificateConverter()).setProvider("BC").getCertificate(certificateBuilder.build(signer));
    }

    public X509Certificate createCACert(PublicKey publicKey, PrivateKey privateKey) throws Exception {
        X500Name issuerName = new X500Name("CN=www.mockserver.com, O=MockServer, L=London, ST=England, C=UK");
        BigInteger serial = BigInteger.valueOf((long)(new Random()).nextInt());
        X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(issuerName, serial, NOT_BEFORE, NOT_AFTER, issuerName, publicKey);
        builder.addExtension(Extension.subjectKeyIdentifier, false, createSubjectKeyIdentifier(publicKey));
        builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));
        KeyUsage usage = new KeyUsage(182);
        builder.addExtension(Extension.keyUsage, false, usage);
        ASN1EncodableVector purposes = new ASN1EncodableVector();
        purposes.add(KeyPurposeId.id_kp_serverAuth);
        purposes.add(KeyPurposeId.id_kp_clientAuth);
        purposes.add(KeyPurposeId.anyExtendedKeyUsage);
        builder.addExtension(Extension.extendedKeyUsage, false, new DERSequence(purposes));
        X509Certificate cert = signCertificate(builder, privateKey);
        cert.checkValidity(new Date());
        cert.verify(publicKey);
        return cert;
    }

    public X509Certificate createClientCert(PublicKey publicKey, X509Certificate certificateAuthorityCert, PrivateKey certificateAuthorityPrivateKey, PublicKey certificateAuthorityPublicKey, String domain, String[] subjectAlternativeNameDomains, String[] subjectAlternativeNameIps) throws Exception {
        X500Name issuer = (new X509CertificateHolder(certificateAuthorityCert.getEncoded())).getSubject();
        X500Name subject = new X500Name("CN=" + domain + ", O=MockServer, L=London, ST=England, C=UK");
        BigInteger serial = BigInteger.valueOf((long)(new Random()).nextInt());
        X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(issuer, serial, NOT_BEFORE, NOT_AFTER, subject, publicKey);
        builder.addExtension(Extension.subjectKeyIdentifier, false, createSubjectKeyIdentifier(publicKey));
        builder.addExtension(Extension.basicConstraints, false, new BasicConstraints(false));
        List<ASN1Encodable> subjectAlternativeNames = new ArrayList();
        String[] var13;
        int var14;
        int var15;
        String subjectAlternativeNameIp;
        if (subjectAlternativeNameDomains != null) {
            subjectAlternativeNames.add(new GeneralName(2, domain));
            var13 = subjectAlternativeNameDomains;
            var14 = subjectAlternativeNameDomains.length;

            for(var15 = 0; var15 < var14; ++var15) {
                subjectAlternativeNameIp = var13[var15];
                subjectAlternativeNames.add(new GeneralName(2, subjectAlternativeNameIp));
            }
        }

        if (subjectAlternativeNameIps != null) {
            var13 = subjectAlternativeNameIps;
            var14 = subjectAlternativeNameIps.length;

            for(var15 = 0; var15 < var14; ++var15) {
                subjectAlternativeNameIp = var13[var15];
                if (IPAddress.isValidIPv6WithNetmask(subjectAlternativeNameIp) || IPAddress.isValidIPv6(subjectAlternativeNameIp) || IPAddress.isValidIPv4WithNetmask(subjectAlternativeNameIp) || IPAddress.isValidIPv4(subjectAlternativeNameIp)) {
                    subjectAlternativeNames.add(new GeneralName(7, subjectAlternativeNameIp));
                }
            }
        }

        if (subjectAlternativeNames.size() > 0) {
            DERSequence subjectAlternativeNamesExtension = new DERSequence((ASN1Encodable[])subjectAlternativeNames.toArray(new ASN1Encodable[subjectAlternativeNames.size()]));
            builder.addExtension(Extension.subjectAlternativeName, false, subjectAlternativeNamesExtension);
        }

        X509Certificate cert = signCertificate(builder, certificateAuthorityPrivateKey);
        cert.checkValidity(new Date());
        cert.verify(certificateAuthorityPublicKey);
        return cert;
    }

    KeyStore generateCertificate(KeyStore keyStore, String certificationAlias, String certificateAuthorityAlias, char[] keyStorePassword, String domain, String[] subjectAlternativeNameDomains, String[] subjectAlternativeNameIps) throws Exception {
        KeyPair keyPair = generateKeyPair(1024);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey caPrivateKey = this.loadPrivateKeyFromPEMFile("cert/CertificateAuthorityPrivateKey.pem");
        X509Certificate caCert = (X509Certificate)this.loadCertificateFromKeyStore("cert/CertificateAuthorityKeyStore.jks", certificateAuthorityAlias, keyStorePassword);
        X509Certificate clientCert = this.createClientCert(publicKey, caCert, caPrivateKey, caCert.getPublicKey(), domain, subjectAlternativeNameDomains, subjectAlternativeNameIps);
        return this.saveCertificateAsKeyStore(keyStore, true, "cert", certificationAlias, privateKey, keyStorePassword, new X509Certificate[]{clientCert, caCert}, caCert);
    }

    public void saveCertificateAsPEMFile(Object x509Certificate, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        JcaPEMWriter jcaPEMWriter = null;

        try {
            jcaPEMWriter = new JcaPEMWriter(fileWriter);
            jcaPEMWriter.writeObject(x509Certificate);
        } finally {
            IOUtils.closeQuietly(jcaPEMWriter);
            IOUtils.closeQuietly(fileWriter);
        }

    }

    private KeyStore saveCertificateAsKeyStore(KeyStore existingKeyStore, boolean deleteOnExit, String keyStoreFileName, String certificationAlias, Key privateKey, char[] keyStorePassword, Certificate[] chain, X509Certificate caCert) {
        try {
            KeyStore keyStore = existingKeyStore;
            if (existingKeyStore == null) {
                keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load((InputStream)null, keyStorePassword);
            }

            try {
                keyStore.deleteEntry(certificationAlias);
            } catch (KeyStoreException var19) {
            }

            keyStore.setKeyEntry(certificationAlias, privateKey, keyStorePassword, chain);

            try {
                keyStore.deleteEntry("server-ca-cert");
            } catch (KeyStoreException var18) {
            }

            keyStore.setCertificateEntry("server-ca-cert", caCert);
            File keyStoreFile = new File(keyStoreFileName);
            FileOutputStream fileOutputStream = null;

            try {
                fileOutputStream = new FileOutputStream(keyStoreFile);
                keyStore.store(fileOutputStream, keyStorePassword);
            } finally {
                IOUtils.closeQuietly(fileOutputStream);
            }

            if (deleteOnExit) {
                keyStoreFile.deleteOnExit();
            }

            return keyStore;
        } catch (Exception var20) {
            throw new RuntimeException("Exception while saving KeyStore", var20);
        }
    }

    private RSAPrivateKey loadPrivateKeyFromPEMFile(String privateKeyFileName) {
        try {
            String publicKeyFile = IOUtils.toString(new InputStreamReader(KeyStoreFactory.class.getClassLoader().getResourceAsStream(privateKeyFileName)));
            byte[] publicKeyBytes = DatatypeConverter.parseBase64Binary(publicKeyFile.replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", ""));
            return (RSAPrivateKey)KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(publicKeyBytes));
        } catch (Exception var4) {
            throw new RuntimeException("Exception reading private key from PEM file", var4);
        }
    }

    public Certificate loadCertificateFromPemFile(String certPemFileName) {
        try {
            InputStream inputStream = RsaKeyFileUtil.readFileFromClassPathOrPath(certPemFileName);

            X509Certificate var6;
            try {
                CertificateFactory fact = CertificateFactory.getInstance("X.509");
                X509Certificate cer = (X509Certificate)fact.generateCertificate(inputStream);
                PublicKey key = cer.getPublicKey();
                var6 = cer;
            } finally {
                IOUtils.closeQuietly(inputStream);
            }

            return var6;
        } catch (Exception var11) {
            throw new RuntimeException("Exception while loading PEM file from " + certPemFileName, var11);
        }
    }

    private Certificate loadCertificateFromKeyStore(String keyStoreFileName, String certificationAlias, char[] keyStorePassword) {
        try {
            InputStream inputStream = RsaKeyFileUtil.readFileFromClassPathOrPath(keyStoreFileName);

            Certificate var6;
            try {
                KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
                keystore.load(inputStream, keyStorePassword);
                var6 = keystore.getCertificate(certificationAlias);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }

            return var6;
        } catch (Exception var11) {
            throw new RuntimeException("Exception while loading KeyStore from " + keyStoreFileName, var11);
        }
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
        NOT_BEFORE = new Date(System.currentTimeMillis() - 31536000000L);
        NOT_AFTER = new Date(System.currentTimeMillis() + 3153600000000L);
    }
}
