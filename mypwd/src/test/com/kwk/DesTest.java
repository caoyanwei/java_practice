package com.kwk;

import org.junit.Test;

public class DesTest {
    @Test
    public void test1() {
        String secretHead = Des.encrypt(Article.PLAIN_HEAD, "123456789");
        String secretHead2 = Des.encrypt(Article.PLAIN_HEAD, "12345678");
        System.out.println(secretHead);
        System.out.println(secretHead2);
    }
}
