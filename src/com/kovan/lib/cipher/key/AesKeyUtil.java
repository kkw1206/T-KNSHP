package com.kovan.lib.cipher.key;

import com.kovan.lib.cipher.ca.CaException;
import com.kovan.lib.cipher.impl.AesCipherImpl;
import org.apache.commons.io.FileUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public final class AesKeyUtil {
    public static final String ALGORITHM_NAME = "AES";
    private static AesCipherImpl aes = new AesCipherImpl();

    public AesKeyUtil() {
    }

    public static String generateSymmetricKeyString(int keyBits) {
        String generatedKeyString = null;
        SecretKey key = generateSymmetricKey(keyBits);
        if (key != null) {
            generatedKeyString = DatatypeConverter.printBase64Binary(key.getEncoded());
        }

        return generatedKeyString;
    }

    public static String generateSymmetricKeyString(int keyBits, String algorithm) throws Exception {
        String generatedKeyString = null;
        SecretKey key = generateSymmetricKey(keyBits, algorithm);
        if (key != null) {
            generatedKeyString = DatatypeConverter.printBase64Binary(key.getEncoded());
        }

        return generatedKeyString;
    }

    public static SecretKey generateSymmetricKey(int keyBits) {
        Object var1 = null;

        try {
            SecureRandom rand = new SecureRandom();
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(rand);
            generator.init(keyBits);
            SecretKey key = generator.generateKey();
            return key;
        } catch (NoSuchAlgorithmException var6) {
            throw new RuntimeException("Can't generate Symmetric Key - " + var6.getMessage());
        }
    }

    public static SecretKey generateSymmetricKey(int keyBits, String algorithm) throws NoSuchAlgorithmException {
        String generatedKeyString = null;
        SecureRandom rand = new SecureRandom();
        KeyGenerator generator = KeyGenerator.getInstance(algorithm);
        generator.init(rand);
        generator.init(keyBits);
        SecretKey key = generator.generateKey();
        return key;
    }

    public static void saveKeyStringToFile(File file, SecretKey key, SecretKey masterKey) {
        String base64KeyString = null;

        try {
            File dir = new File(file.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }

            base64KeyString = aes.encryptBase64(key.getEncoded(), masterKey);
            FileUtils.writeStringToFile(file, base64KeyString, "UTF-8");
        } catch (Exception var5) {
            throw new CaException("saveKeyStringToFile error");
        }
    }

    public static void saveStringToFile(File file, String saveToString) {
        try {
            File dir = new File(file.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileUtils.writeStringToFile(file, saveToString, "UTF-8");
        } catch (IOException var3) {
            throw new CaException(var3);
        }
    }

    public static void saveKeyStringToFile(String fileName, SecretKey key, SecretKey masterKey) {
        File file = new File(fileName);
        saveKeyStringToFile(file, key, masterKey);
    }

    public static void saveStringToFile(String fileName, String saveToString) {
        File file = new File(fileName);
        saveStringToFile(file, saveToString);
    }

    public static String loadKeyFromFileString(String fileName) {
        File file = new File(fileName);
        return loadKeyFromFileString(file);
    }

    private static String loadKeyFromFileString(File file) {
        String fileString = null;

        try {
            fileString = FileUtils.readFileToString(file, "UTF-8");
            return fileString;
        } catch (IOException var3) {
            throw new CaException(var3);
        }
    }

    public static SecretKey loadKeyFromFile(File file, SecretKey masterKey) {
        String base64KeyString = null;
        SecretKey key = null;
        byte[] keyBytes = null;
        base64KeyString = loadKeyFromFileString(file);

        try {
            keyBytes = aes.decryptBytesBase64(base64KeyString, masterKey);
            key = new SecretKeySpec(keyBytes, "AES");
        } catch (Exception var6) {
            keyBytes = null;
            key = null;
        }

        return key;
    }

    public static SecretKey loadKeyFromFile(String fileName, SecretKey masterKey) {
        File file = new File(fileName);
        return loadKeyFromFile(file, masterKey);
    }

    public static String generateKeyId() {
        UUID uuid = UUID.randomUUID();
        String keyId = uuid.toString().replace("-", "");
        return keyId;
    }

    public static String mergeKey(String sessionKey, SecretKey masterKey) {
        String mergedKey = null;
        byte[] array_1 = DatatypeConverter.parseBase64Binary(sessionKey);
        byte[] array_2 = masterKey.getEncoded();
        byte[] array_3 = new byte[array_1.length];
        int i = 0;
        byte[] var7 = array_1;
        int var8 = array_1.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            byte b = var7[var9];
            array_3[i] = (byte)(b ^ array_2[i++]);
        }

        mergedKey = DatatypeConverter.printBase64Binary(array_3);
        return mergedKey;
    }
}
