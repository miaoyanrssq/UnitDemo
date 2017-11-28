package com.zjrb.bizman.utils_component.utils;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by lujialei
 */
public class EncryptUtils {
    
    private final static String DES = "DES";
    private static final String CIPHER = "DES/CBC/PKCS5Padding";
    public static String IV_PARAM = "12345678";
    public static String IV_PARAM_KEY = "ivParam";
    
    public static void main(String[] args) throws Exception {
        String data = "123 456";
        String key = "wang!@#$%";
//        System.err.println(encrypt(data, key, IV_PARAM));
//        System.err.println(decrypt(encrypt(data, key, IV_PARAM), key));
        
    }
    
    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key, String ivParam) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes(), ivParam.getBytes());
        String strs = Base64Tool.encode(bt);
        return strs;
    }
    
    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key, String ivParam) throws
            Exception {
        if (data == null)
            return null;
        byte[] buf = Base64Tool.decode(data);
        byte[] bt = decrypt(buf, key.getBytes(), ivParam.getBytes());
        return new String(bt);
    }
    
    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key, byte[] ivParam) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(CIPHER);
        
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, new IvParameterSpec(ivParam), sr);
        
        return cipher.doFinal(data);
    }
    
    
    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key, byte[] ivParam) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(CIPHER);
        
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, new IvParameterSpec(ivParam), sr);
        
        return cipher.doFinal(data);
    }
}
