package com.kwk;

import org.junit.Test;

public class HashHelperTest {
    @Test
    public void test1() {
        byte[] bs = HashHelper.md5("abc");
        System.out.println(bs.length);
    }
}
