package com.yh.test;

import sun.misc.BASE64Encoder;

import java.awt.image.ImagingOpException;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode("123456".getBytes("utf-8"));
        System.out.println(encode);
        String encode1 = encoder.encode(encode.getBytes("utf-8"));
        System.out.println(encode1);

    }
}
