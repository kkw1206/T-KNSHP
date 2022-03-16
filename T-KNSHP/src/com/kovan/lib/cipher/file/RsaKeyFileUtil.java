package com.kovan.lib.cipher.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public final class RsaKeyFileUtil {
    public RsaKeyFileUtil() {
    }

    public static InputStream readFileFromClassPathOrPath(String keyStoreFileName) throws FileNotFoundException {
        InputStream inputStream = RsaKeyFileUtil.class.getClassLoader().getResourceAsStream(keyStoreFileName);
        if (inputStream == null) {
            inputStream = new FileInputStream(keyStoreFileName);
        }

        return (InputStream)inputStream;
    }
}
