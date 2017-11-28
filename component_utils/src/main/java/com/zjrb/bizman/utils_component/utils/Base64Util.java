package com.zjrb.bizman.utils_component.utils;

/**
 * Created by lujialei
 */
public class Base64Util {
    private final static int flags = 0x110;
    
    /***
     * encode by Base64
     */
    public static String encodeBase64(byte[] input) throws Exception {
        return String.valueOf(Base64.encode(input, flags));
    }
    
    /***
     * decode by Base64
     */
    public static byte[] decodeBase64(String input) throws Exception {
        return Base64.decode(input, flags);
    }
}
