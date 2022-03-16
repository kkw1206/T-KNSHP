package com.kovan.lib.cipher.ca.certificate.impl;


import com.kovan.lib.cipher.ca.CaException;
import com.kovan.lib.cipher.ca.certificate.CertificateWithPrivateKey;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

class CertificateWithPrivateKeyImpl extends CertificateImpl implements CertificateWithPrivateKey {
    static final String KEYSTORE_TYPE = "PKCS12";
    private final PrivateKey privateKey;

    CertificateWithPrivateKeyImpl(X509Certificate certificate, PrivateKey privateKey) {
        super(certificate);
        this.privateKey = privateKey;
    }

    public KeyStore addToKeystore(KeyStore keyStore, String alias) {
        try {
            X509Certificate certificate = this.getX509Certificate();
            Certificate[] chain = new Certificate[]{certificate};
            keyStore.setKeyEntry(alias, this.privateKey, (char[])null, chain);
            return keyStore;
        } catch (KeyStoreException var5) {
            throw new CaException(var5);
        }
    }

    public KeyStore saveInPkcs12Keystore(String alias) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load((InputStream)null, (char[])null);
            this.addToKeystore(keyStore, alias);
            return keyStore;
        } catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException var3) {
            throw new CaException(var3);
        }
    }

    public void exportPkcs12(String keystorePath, char[] keystorePassword, String alias) {
        File file = new File(keystorePath);
        this.exportPkcs12(file, keystorePassword, alias);
    }

    public void exportPkcs12(File keystoreFile, char[] keystorePassword, String alias) {
        try {
            File dir = new File(keystoreFile.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }

            KeyStore keyStore;
            Throwable var7;
            if (keystoreFile.exists() && keystoreFile.isFile()) {
                keyStore = KeyStore.getInstance("PKCS12");
                InputStream stream = new FileInputStream(keystoreFile);
                var7 = null;

                try {
                    keyStore.load(stream, keystorePassword);
                } catch (Throwable var33) {
                    var7 = var33;
                    throw var33;
                } finally {
                    if (stream != null) {
                        if (var7 != null) {
                            try {
                                stream.close();
                            } catch (Throwable var30) {
                                var7.addSuppressed(var30);
                            }
                        } else {
                            stream.close();
                        }
                    }

                }

                this.addToKeystore(keyStore, alias);
            } else {
                keyStore = this.saveInPkcs12Keystore(alias);
            }

            OutputStream stream = new FileOutputStream(keystoreFile);
            var7 = null;

            try {
                keyStore.store(stream, keystorePassword);
            } catch (Throwable var32) {
                var7 = var32;
                throw var32;
            } finally {
                if (stream != null) {
                    if (var7 != null) {
                        try {
                            stream.close();
                        } catch (Throwable var31) {
                            var7.addSuppressed(var31);
                        }
                    } else {
                        stream.close();
                    }
                }

            }

        } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException var36) {
            throw new CaException(var36);
        }
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public String printPrivateKey() {
        StringWriter sw = new StringWriter();

        try {
            JcaPEMWriter writer = new JcaPEMWriter(sw);
            Throwable var3 = null;

            String var4;
            try {
                writer.writeObject(this.privateKey);
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

    public void savePrivateKey(File file) {
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
                    writer.writeObject(this.privateKey);
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

    public void savePrivateKey(String fileName) {
        File file = new File(fileName);
        this.savePrivateKey(file);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String toJsonString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
