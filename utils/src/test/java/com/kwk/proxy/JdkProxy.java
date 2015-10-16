package com.kwk.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {
    @Test
    public void test1() {
        Itf itf = (Itf) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Itf.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return 10;
            }
        });
        System.out.println(itf.add(1, 2));
    }

    public static interface Itf {
        public int add(int a, int b);
    }
}
