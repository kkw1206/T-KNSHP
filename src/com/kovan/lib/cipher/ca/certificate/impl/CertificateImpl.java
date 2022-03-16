package com.kovan.lib.cipher.ca.certificate.impl;


import com.kovan.lib.cipher.ca.CaException;
import com.kovan.lib.cipher.ca.certificate.Certificate;
import com.kovan.lib.cipher.ca.certificate.CertificateWithPrivateKey;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

public class CertificateImpl implements Certificate {
    private final X509Certificate certificate;

    public CertificateImpl(X509Certificate certificate) {
        this.certificate = certificate;
    }

    public X509Certificate getX509Certificate() {
        return this.certificate;
    }

    public PublicKey getPublicKey() {
        return this.certificate.getPublicKey();
    }

    public String print() {
        StringWriter sw = new StringWriter();

        try {
            JcaPEMWriter writer = new JcaPEMWriter(sw);
            Throwable var3 = null;

            String var4;
            try {
                writer.writeObject(this.certificate);
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

    public String printPublicKey() {
        StringWriter sw = new StringWriter();

        try {
            JcaPEMWriter writer = new JcaPEMWriter(sw);
            Throwable var3 = null;

            String var4;
            try {
                writer.writeObject(this.certificate.getPublicKey());
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

    public void save(File file) {
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
                    writer.writeObject(this.certificate);
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

    public void save(String fileName) {
        File file = new File(fileName);
        this.save(file);
    }

    public void savePublicKey(File file) {
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
                    writer.writeObject(this.certificate.getPublicKey());
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

    public void savePublicKey(String fileName) {
        File file = new File(fileName);
        this.savePublicKey(file);
    }

    public CertificateWithPrivateKey attachPrivateKey(PrivateKey privateKey) {
        return new CertificateWithPrivateKeyImpl(this.certificate, privateKey);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String toJsonString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
