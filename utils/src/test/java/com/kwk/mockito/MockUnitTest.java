package com.kwk.mockito;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MockUnitTest {
    @InjectMocks
    private ServiceImpl service;

    @Mock
    private InvokedItf itf;

    @Before
    public void init() {
        service = new ServiceImpl(10);
        MockitoAnnotations.initMocks(this);
        //Mockito.when(itf.add(1, 2)).thenReturn(3);
        Mockito.doReturn(3).when(itf).add(1, 2);
    }

    @Test
    public void basicTest() {
        int ret = service.myMethod(1, 2);
        System.out.println(ret);
    }

    @Test
    public void partialMockTest() {
        Jack spyJack = Mockito.spy(new Jack());
        //Mockito.when(spyJack.go()).thenReturn(false);这种写法会调用实际的go方法
        Mockito.doReturn(false).when(spyJack).go();
        Assert.assertFalse(spyJack.go());
    }
}

class Jack {
    public boolean go() {
        System.out.println("I say go go go!!");
        return true;
    }
}
