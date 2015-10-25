package com.kwk;

import com.google.common.base.Charsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashHelper {
    private final static String KEY_MD5 = "MD5";

//    public static String md5(String input) {
//        byte[] buf = input.getBytes(Charsets.UTF_8);
//        MessageDigest md5 = null;
//        try {
//            md5 = MessageDigest.getInstance(KEY_MD5);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            throw new IllegalArgumentException();
//        }
//        md5.update(buf);
//        byte[] outBuf = md5.digest();
//        char[] str = Hex.encodeHex(outBuf);
//        return new String(str);
//    }

    public static byte[] md5(String input) {
        byte[] buf = input.getBytes(Charsets.UTF_8);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(KEY_MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        md5.update(buf);
        byte[] outBuf = md5.digest();
        return outBuf;
    }

}
