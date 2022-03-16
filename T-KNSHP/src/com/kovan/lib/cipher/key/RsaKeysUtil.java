package com.kovan.lib.cipher.key;


import com.kovan.lib.cipher.ca.CaException;
import com.kovan.lib.cipher.cert.CertUtil;
import com.kovan.lib.cipher.file.RsaKeyFileUtil;
import com.kovan.lib.cipher.provider.DefaultSecurityProvider;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.*;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public final class RsaKeysUtil {
    private static final String ALGORITHM = "RSA";
    private static final int DEFAULT_KEY_SIZE = 2048;
    private static int keySize;

    public RsaKeysUtil() {
    }

    public static KeyPair generateKeyPair() {
        return generateKeyPair(2048);
    }

    public static KeyPair generateKeyPair(int keySize) {
        KeyPair keyPair = null;

        try {
            setKeySize(keySize);
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA", DefaultSecurityProvider.getProvider());
            SecureRandom random = new SecureRandom();
            kpGen.initialize(keySize, random);
            keyPair = kpGen.generateKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException var4) {
            return null;
        } catch (NoSuchProviderException var5) {
            return null;
        }
    }

    public static String getKeyAlgorithm() {
        return "RSA";
    }

    public static int getKeySize() {
        return keySize;
    }

    public static void setKeySize(int keySize) {
        RsaKeysUtil.keySize = keySize;
    }

    public static RSAPrivateKey loadPrivateKeyFromPemFile(String privateKeyFileName) {
        try {
            String privateKeyPemContents = IOUtils.toString(new InputStreamReader(RsaKeyFileUtil.readFileFromClassPathOrPath(privateKeyFileName)));
            byte[] privateKeyBytes = DatatypeConverter.parseBase64Binary(privateKeyPemContents.replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", ""));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA", DefaultSecurityProvider.getProvider());
            RSAPrivateKey privKey = (RSAPrivateKey)kf.generatePrivate(keySpec);
            return privKey;
        } catch (Exception var6) {
            throw new RuntimeException("Exception reading private key from PEM file", var6);
        }
    }

    public static RSAPublicKey loadPublicKeyFromPemFile(String publicKeyFileName) {
        try {
            String publicKeyFileContents = IOUtils.toString(new InputStreamReader(RsaKeyFileUtil.readFileFromClassPathOrPath(publicKeyFileName)));
            byte[] publicKeyBytes = DatatypeConverter.parseBase64Binary(publicKeyFileContents.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", ""));
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA", DefaultSecurityProvider.getProvider());
            RSAPublicKey publicKey = (RSAPublicKey)kf.generatePublic(keySpec);
            return publicKey;
        } catch (Exception var6) {
            throw new RuntimeException("Exception reading public key from PEM Cert file", var6);
        }
    }

    public static RSAPublicKey loadPublicKeyFromCertPemFile(String certPemFileName) throws Exception {
        Certificate cert = CertUtil.loadCertificateFromPemFile(certPemFileName);
        RSAPublicKey puk = (RSAPublicKey)cert.getPublicKey();
        return puk;
    }

    public static void savePrivateKey(String fileName, PrivateKey key) {
        File file = new File(fileName);
        saveKey(file, key);
    }

    public static void savePublicKey(String fileName, PublicKey key) {
        File file = new File(fileName);
        saveKey(file, key);
    }

    public static void saveKey(File file, Key key) {
        try {
            File dir = new File(file.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }

            BufferedWriter fw = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            Throwable var4 = null;

            try {
                JcaPEMWriter writer = new JcaPEMWriter(fw);
                Throwable var6 = null;

                try {
                    writer.writeObject(key);
                    writer.flush();
                } catch (Throwable var31) {
                    var6 = var31;
                    throw var31;
                } finally {
                    if (writer != null) {
                        if (var6 != null) {
                            try {
                                writer.close();
                            } catch (Throwable var30) {
                                var6.addSuppressed(var30);
                            }
                        } else {
                            writer.close();
                        }
                    }

                }
            } catch (Throwable var33) {
                var4 = var33;
                throw var33;
            } finally {
                if (fw != null) {
                    if (var4 != null) {
                        try {
                            fw.close();
                        } catch (Throwable var29) {
                            var4.addSuppressed(var29);
                        }
                    } else {
                        fw.close();
                    }
                }

            }

        } catch (IOException var35) {
            throw new CaException(var35);
        }
    }

    public static String printKey(Key key) {
        StringWriter sw = new StringWriter();

        try {
            JcaPEMWriter writer = new JcaPEMWriter(sw);
            Throwable var3 = null;

            String var4;
            try {
                writer.writeObject(key);
                writer.flush();
                var4 = sw.toString();
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if (writer != null) {
                    if (var3 != null) {
                        try {
                            writer.close();
                        } catch (Throwable var13) {
                            var3.addSuppressed(var13);
                        }
                    } else {
                        writer.close();
                    }
                }

            }

            return var4;
        } catch (IOException var16) {
            throw new CaException(var16);
        }
    }
}
