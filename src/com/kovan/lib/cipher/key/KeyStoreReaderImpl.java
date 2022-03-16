package com.kovan.lib.cipher.key;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class KeyStoreReaderImpl implements KeyStoreReader {
    static final String KEYSTORE_TYPE = "PKCS12";

    public KeyStoreReaderImpl() {
    }

    public List<String> listAliases(String keyStorePath, char[] password) {
        File file = new File(keyStorePath);
        return this.listAliases(file, password);
    }

    public List<String> listAliases(File keyStoreFile, char[] password) {
        try {
            if (keyStoreFile.exists() && keyStoreFile.isFile()) {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                InputStream stream = new FileInputStream(keyStoreFile);
                keyStore.load(stream, password);
                return this.listAliases(keyStore);
            } else {
                return new ArrayList();
            }
        } catch (KeyStoreException var5) {
            throw new RuntimeException(var5);
        } catch (IOException var6) {
            throw new RuntimeException(var6);
        } catch (CertificateException var7) {
            throw new RuntimeException(var7);
        } catch (NoSuchAlgorithmException var8) {
            throw new RuntimeException(var8);
        }
    }

    public List<String> listAliases(KeyStore keyStore) {
        ArrayList res = new ArrayList();

        try {
            Enumeration aliases = keyStore.aliases();

            while(aliases.hasMoreElements()) {
                res.add(aliases.nextElement());
            }

            return res;
        } catch (KeyStoreException var4) {
            throw new RuntimeException(var4);
        }
    }
}
