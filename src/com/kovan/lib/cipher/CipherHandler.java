package com.kovan.lib.cipher;


import com.kovan.lib.cipher.exception.UserDefineException;
import com.kovan.lib.cipher.impl.*;
import com.kovan.lib.cipher.key.AesKeyUtil;
import com.kovan.lib.cipher.key.RsaKeysUtil;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class CipherHandler {
    private static final String classBaseURI = "com.kovan.lib.cipher.";
    private final String CHARSET = "UTF-8";
    private final String IV = "9876543219876543";
    private static CipherHandler cipherHandler = null;
    private static CipherBase cipherBase = null;

    public CipherHandler() {
    }

    public static CipherHandler getInstance() {
        if (cipherHandler == null) {
            cipherHandler = new CipherHandler();
            return cipherHandler;
        } else {
            return cipherHandler;
        }
    }

    public void Init(String type, String domain) {
        try {
            cipherBase = getInstance().createObj(type);
            cipherBase.Init(domain);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    protected CipherBase createObj(String type) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (cipherBase == null) {
            String classBaseURI = "com.kovan.lib.cipher." + type;
            Class cls = Class.forName(classBaseURI);
            Object object = cls.newInstance();
            return (CipherBase)object;
        } else {
            return cipherBase;
        }
    }

    public String encryptBase64AES(String plainText, String key) throws Exception {
        return cipherBase.encryptBase64AES(plainText, key, "9876543219876543".getBytes("UTF-8"), "UTF-8");
    }

    public String encryptBase64AES(String plainText, String key, String iv) throws Exception {
        return cipherBase.encryptBase64AES(plainText, key, iv.getBytes("UTF-8"), "UTF-8");
    }

    public Map encryptBase64AES(HashMap<String, String> plainMap, String key) throws Exception {
        ConcurrentHashMap<String, String> tempMap = new ConcurrentHashMap();
        Iterator var4 = plainMap.entrySet().iterator();

        while(var4.hasNext()) {
            Entry<String, String> entry = (Entry)var4.next();
            String mapkey = (String)entry.getKey();
            String val = (String)entry.getValue();
            if (val != null && val.length() > 0) {
                String tempCipherText = cipherBase.encryptBase64AES(val, key, "9876543219876543".getBytes("UTF-8"), "UTF-8");
                tempMap.put(mapkey, tempCipherText);
            }
        }

        Map resultMap = new HashMap();
        resultMap.putAll(tempMap);
        return resultMap;
    }

    public Map encryptBase64AES(HashMap<String, String> plainMap, String key, String iv) throws Exception {
        ConcurrentHashMap<String, String> tempMap = new ConcurrentHashMap();
        Iterator var5 = plainMap.entrySet().iterator();

        while(var5.hasNext()) {
            Entry<String, String> entry = (Entry)var5.next();
            String mapkey = (String)entry.getKey();
            String val = (String)entry.getValue();
            if (val != null && val.length() > 0) {
                String tempCipherText = cipherBase.encryptBase64AES(val, key, iv.getBytes("UTF-8"), "UTF-8");
                tempMap.put(mapkey, tempCipherText);
            }
        }

        Map resultMap = new HashMap();
        resultMap.putAll(tempMap);
        return resultMap;
    }

    public String decryptBase64AES(String cipherText, String key) throws Exception {
        return cipherBase.decryptBase64AES(cipherText, key, "9876543219876543".getBytes("UTF-8"), "UTF-8");
    }

    public String decryptBase64AES(String cipherText, String key, String iv) throws Exception {
        return cipherBase.decryptBase64AES(cipherText, key, iv.getBytes("UTF-8"), "UTF-8");
    }

    public Map decryptBase64AES(HashMap<String, String> cipherMap, String key) throws Exception {
        ConcurrentHashMap<String, String> tempMap = new ConcurrentHashMap();
        Iterator var4 = cipherMap.entrySet().iterator();

        while(var4.hasNext()) {
            Entry<String, String> entry = (Entry)var4.next();
            String mapkey = (String)entry.getKey();
            String val = (String)entry.getValue();
            if (val != null && val.length() > 0) {
                String tempCipherText = cipherBase.decryptBase64AES(val, key, "9876543219876543".getBytes("UTF-8"), "UTF-8");
                tempMap.put(mapkey, tempCipherText);
            }
        }

        Map resultMap = new HashMap();
        resultMap.putAll(tempMap);
        return resultMap;
    }

    public Map decryptBase64AES(HashMap<String, String> cipherMap, String key, String iv) throws Exception {
        ConcurrentHashMap<String, String> tempMap = new ConcurrentHashMap();
        Iterator var5 = cipherMap.entrySet().iterator();

        while(var5.hasNext()) {
            Entry<String, String> entry = (Entry)var5.next();
            String mapkey = (String)entry.getKey();
            String val = (String)entry.getValue();
            if (val != null && val.length() > 0) {
                String tempCipherText = cipherBase.decryptBase64AES(val, key, iv.getBytes("UTF-8"), "UTF-8");
                tempMap.put(mapkey, tempCipherText);
            }
        }

        Map resultMap = new HashMap();
        resultMap.putAll(tempMap);
        return resultMap;
    }

    public String encryptBase64TDES(String plainText, String sessionKey, byte[] iv, String charset) throws Exception {
        String rtnStr = null;
        TDesCipherImpl Cipher = new TDesCipherImpl();
        rtnStr = Cipher.encryptBase64(plainText, charset, sessionKey, iv);
        return rtnStr;
    }

    public String decryptBase64TDES(String cipherText, String sessionKey, byte[] iv, String charset) throws Exception {
        String rtnStr = null;
        TDesCipherImpl Cipher = new TDesCipherImpl();
        rtnStr = Cipher.decryptBase64(cipherText, charset, sessionKey, iv);
        return rtnStr;
    }

    public String getHmac(String salt, Map input, String algorithm) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {
        String rtnStr = null;
        TreeMap<String, String> tempMap = new TreeMap(input);
        StringBuffer sb = new StringBuffer();

        String val;
        for(Iterator var7 = tempMap.entrySet().iterator(); var7.hasNext(); sb.append(val)) {
            Entry<String, String> entry = (Entry)var7.next();
            val = (String)entry.getValue();
            if (val == null && val.length() < 1) {
                val = "";
            }
        }

        Hmac hmac = new HmacImplSha256();
        rtnStr = hmac.getHmac(sb.toString(), salt, algorithm);
        return rtnStr;
    }

    public String getHmac(String salt, List<String> input, String algorithm) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {
        String rtnStr = null;
        new HashMap();

        try {
            StringBuffer sb = new StringBuffer();

            String val;
            for(Iterator var7 = input.iterator(); var7.hasNext(); sb.append(val)) {
                val = (String)var7.next();
                if (val == null && val.length() < 1) {
                    val = "";
                }
            }

            Hmac hmac = new HmacImplSha256();
            rtnStr = hmac.getHmac(sb.toString(), salt, algorithm);
            return rtnStr;
        } catch (Exception var9) {
            throw new UserDefineException(var9, "E001", "HMAC 암호화 오류");
        }
    }

    public String getSha256(String input) throws Exception {
        String ret = null;
        String key = "PGTNC20151225";
        HashImplSha256 sha = new HashImplSha256();
        ret = sha.getSha256(input, key);
        return ret;
    }

    public String getSha512(String input) throws Exception {
        String ret = null;
        String key = "PGTNC20151225";
        HashImplSha512 sha = new HashImplSha512();
        ret = sha.getSha512(input, key);
        return ret;
    }

    public String generateSymmetricKeyString(int keyByte, String algorithm) throws Exception {
        String rtnStr = null;
        rtnStr = AesKeyUtil.generateSymmetricKeyString(keyByte, algorithm);
        return rtnStr;
    }

    public String encryptBase64RSA(String plainText, String publicKey, String charset, String transformations) throws Exception {
        String rtnStr = null;
        RSAPublicKey puk = RsaKeysUtil.loadPublicKeyFromCertPemFile(publicKey);
        rtnStr = (new RsaCipherImpl()).encryptBase64(plainText, charset, puk, transformations);
        return rtnStr;
    }

    public String decryptBase64RSA(String cipherText, String privateKey, String charset, String transformations) throws Exception {
        String rtnStr = null;
        RSAPrivateKey pk = RsaKeysUtil.loadPrivateKeyFromPemFile(privateKey);
        rtnStr = (new RsaCipherImpl()).decryptBase64(cipherText, charset, pk, transformations);
        return rtnStr;
    }

    public byte[] encryptSEED(byte[] pbData, byte[] key) {
        byte[] pbCipher = null;
        pbCipher = SeedKisaImpl.SEED_ECB_Encrypt_long(key, pbData, 0, pbData.length);
        return pbCipher;
    }

    public byte[] decryptSEED(byte[] cipherText, byte[] key) {
        byte[] pbPlain = null;
        pbPlain = SeedKisaImpl.SEED_ECB_Decrypt_long(key, cipherText, 0, cipherText.length);
        return pbPlain;
    }
}
