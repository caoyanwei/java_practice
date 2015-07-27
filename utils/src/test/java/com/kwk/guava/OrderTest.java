package com.kwk.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.List;

public class OrderTest {

    @Test
    public void basicTest1() {
        List<Integer> intList = Lists.newArrayList(10, 20, 5, 3, 15, 60);
        List<Integer> top = Ordering.natural().greatestOf(intList, 3);
        System.out.println(top);
    }
}
