package com.kwk.bigdecimal;

import org.junit.Test;

import java.math.BigDecimal;

public class BasicTest {

    @Test
    public void test1() {
        BigDecimal a = new BigDecimal("998.01");
        System.out.println(a);
        BigDecimal b=new BigDecimal("100");
        System.out.println(a.multiply(b));
    }
}
