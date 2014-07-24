package com.kwk.classloader;

import org.junit.Test;

public class ClassPathResourceTest {
    @Test
    public void test1() {
        System.out.println(this.getClass().getResource(""));
        System.out.println(this.getClass().getResource("/"));
    }

    @Test
    public void test2() {
        System.out.println(this.getClass().getClassLoader().getResource(""));
        System.out.println(this.getClass().getClassLoader().getResource("/"));//null
    }
}
