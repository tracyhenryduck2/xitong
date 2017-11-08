package com.server.socketserver;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class Util {
 
    public static String decode(String str, String charCode) {
        try {
            return new String(new BASE64Decoder().decodeBuffer(str), charCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public static String encode(byte[] bstr) {
        return new BASE64Encoder().encode(bstr);
    }
}