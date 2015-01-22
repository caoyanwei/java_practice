package com.kwk.reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;

public class ArrayTest {
    @Test
    public void basicTest() throws ClassNotFoundException {
        int[] intArray = (int[]) Array.newInstance(int.class, 3);
        Assert.assertTrue(intArray.length == 3);

        Array.set(intArray, 0, 123);

        Class strClass = Class.forName("[Ljava.lang.String;");
        Assert.assertTrue(strClass.isArray());
    }
}
