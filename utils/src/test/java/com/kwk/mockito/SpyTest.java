package com.kwk.mockito;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;

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

    @Test
    public void basicTest3() {
        Add obj = spy(new Add());
        Mockito.doReturn(3).when(obj).add(10, 20);
        assertEquals(obj.add(10, 20), 3);
        assertEquals(obj.add(100, 200), 300);
    }

    @Test
    public void basicTest4() {
        Add obj = spy(new Add());
        Mockito.doReturn(3).when(obj).add(anyInt(), anyInt());
        assertEquals(obj.add(10, 20), 3);
        assertEquals(obj.add(100, 200), 3);
    }

    @Test
    public void basicTest5() {
        Add obj = spy(new Add());
        //如果用了模糊匹配，则所有参数都要用，这种用法是错的 Mockito.doReturn(3).when(obj).add(anyInt(), 5);
        Mockito.doReturn(3).when(obj).add(anyInt(), eq(5));
        assertEquals(obj.add(10, 5), 3);
        assertEquals(obj.add(10, 20), 30);
    }
}

class Add {
    int add(int a, int b) {
        return a + b;
    }
}
