package com.kwk.math;

import org.junit.Test;

import java.math.BigDecimal;

public class MathTest {
    @Test
    public void floorTest() {
        System.out.println(fen2yuan(120));
        System.out.println(fen2yuan(1550));
        System.out.println(fen2yuan(1540));
    }

    int fen2yuan(long fen) {
        BigDecimal decimal = new BigDecimal((double)fen/100.0).setScale(0, BigDecimal.ROUND_HALF_UP);
        return decimal.toBigInteger().intValue();
    }
}
