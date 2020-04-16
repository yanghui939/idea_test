package com.yh.utils;


import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Base64 {
    /**
     * 加密
     *
     * @param password
     * @return
     * @throws IOException
     */
    public static String encoder(String password) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(password.getBytes("utf-8"));
        return encode;
    }

    /**
     * 解密
     *
     * @param str
     * @return
     * @throws IOException
     */
    public static String decoder(String str) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(str);
        return new String(bytes);
    }

}
