package com.kwk.guava;

import com.google.common.math.IntMath;
import org.junit.Test;

public class IntMathTest {
    @Test
    public void basicTest() {
        IntMath.checkedAdd(1, 1000);
    }
}
