package com.kovan.lib.cipher.key;

import java.io.File;
import java.security.KeyStore;
import java.util.List;

public interface KeyStoreReader {
    List<String> listAliases(String var1, char[] var2);

    List<String> listAliases(File var1, char[] var2);

    List<String> listAliases(KeyStore var1);
}
