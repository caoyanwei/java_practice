package com.kwk.math;


import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    @Test
    public void basicTest() {
        BigDecimal money = new BigDecimal("1.8");
        BigDecimal ret = money.multiply(new BigDecimal("0.71"));
        System.out.println(ret);
    }
}
