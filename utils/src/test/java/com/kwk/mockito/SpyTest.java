package com.kwk.mockito;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class SpyTest {
    @Spy
    private Add add;

    @Test
    public void basicTest() {
        add = new Add();
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(10).when(add).add(1, 2);
        System.out.println(add.add(1, 2));
    }

    @Test
    public void basicTest2() {
        add = new Add();
        MockitoAnnotations.initMocks(this);
        Mockito.when(add.add(1, 2)).thenReturn(10);
        //有add字符串输出，说明实际方法被调用
        System.out.println(add.add(1, 2));
    }
}

class Add {
    int add(int a, int b) {
        System.out.println("add");
        return a + b;
    }
}
