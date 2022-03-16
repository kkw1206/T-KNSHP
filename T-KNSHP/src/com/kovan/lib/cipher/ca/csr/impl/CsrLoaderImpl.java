package com.kovan.lib.cipher.ca.csr.impl;

import com.kovan.lib.cipher.ca.CaException;
import com.kovan.lib.cipher.ca.csr.CSR;
import com.kovan.lib.cipher.ca.csr.CsrLoader;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class CsrLoaderImpl implements CsrLoader {
    private final File file;

    public CsrLoaderImpl(File file) {
        this.file = file;
    }

    public CsrLoaderImpl(String fileName) {
        this(new File(fileName));
    }

    public CSR getCsr() {
        try {
            Reader pemReader = Files.newBufferedReader(this.file.toPath(), StandardCharsets.UTF_8);
            Throwable var2 = null;

            CsrImpl var7;
            try {
                PEMParser pemParser = new PEMParser(pemReader);
                Throwable var4 = null;

                try {
                    Object parsedObj = pemParser.readObject();
                    if (!(parsedObj instanceof PKCS10CertificationRequest)) {
                        throw new CaException("Not a PKCS10CertificationRequest");
                    }

                    PKCS10CertificationRequest csr = (PKCS10CertificationRequest)parsedObj;
                    var7 = new CsrImpl(csr);
                } catch (Throwable var32) {
                    var4 = var32;
                    throw var32;
                } finally {
                    if (pemParser != null) {
                        if (var4 != null) {
                            try {
                                pemParser.close();
                            } catch (Throwable var31) {
                                var4.addSuppressed(var31);
                            }
                        } else {
                            pemParser.close();
                        }
                    }

                }
            } catch (Throwable var34) {
                var2 = var34;
                throw var34;
            } finally {
                if (pemReader != null) {
                    if (var2 != null) {
                        try {
                            pemReader.close();
                        } catch (Throwable var30) {
                            var2.addSuppressed(var30);
                        }
                    } else {
                        pemReader.close();
                    }
                }

            }

            return var7;
        } catch (IOException var36) {
            throw new CaException(var36);
        }
    }
}