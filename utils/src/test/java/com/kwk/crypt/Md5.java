package com.kwk.crypt;

import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
    private final static Logger logger = LoggerFactory.getLogger(Md5.class);

    private final static String KEY_MD5 = "MD5";

    public static String md5(String input) throws NoSuchAlgorithmException {
        byte[] buf = input.getBytes(Charsets.UTF_8);
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(buf);
        byte[] outBuf = md5.digest();
        char[] str = Hex.encodeHex(outBuf);
        return new String(str);
    }
}
