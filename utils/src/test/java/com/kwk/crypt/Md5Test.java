package com.kwk.crypt;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class Md5Test {

    @Test
    public void basicTest() throws NoSuchAlgorithmException {
        String md5 = Md5.md5("aaaaaa");
        System.out.println(md5);
    }
}
