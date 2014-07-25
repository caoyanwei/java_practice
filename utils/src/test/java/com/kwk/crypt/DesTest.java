package com.kwk.crypt;

import org.junit.Test;

public class DesTest {

    @Test
    public void basicTest() throws Exception {
        String data = "hello world";
        String key = "abc&1234";
        String secret = Des.encrypt(data, key);
        String plainData = Des.decrypt(secret, key);
        System.out.println(plainData);
    }
}
