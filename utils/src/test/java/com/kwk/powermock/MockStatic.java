package com.kwk.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SystemPropertyMockDemo.class})
public class MockStatic {
    @Test
    public void demoOfFinalSystemClassMocking() throws Exception {
        PowerMockito.mockStatic(System.class);

        SystemPropertyMockDemo spy = PowerMockito.spy(new SystemPropertyMockDemo());

        Mockito.when(System.getProperty("property")).thenReturn("my property");

        //PowerMockito.doReturn(12).when(spy, "innerMethod", Mockito.anyInt(), Mockito.anyInt());

        PowerMockito.doReturn(12).when(spy, "innerMethod", 10, 10);

        Assert.assertEquals("my property", spy.getSystemProperty());
    }

}

class SystemPropertyMockDemo {
    public String getSystemProperty() throws IOException {
        System.out.println(innerMethod(11, 10));
        return System.getProperty("property");
    }

    private int innerMethod(int a, int b) {
        return a + b + 10;
    }
}
