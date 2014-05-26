package com.kwk.reflect;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    @Test
    public void basicTest() throws Exception {
        System.out.println("Proxy");
        MyItf itf = (MyItf) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{MyItf.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("method:" + method.getName());
                for (Object obj : args) {
                    System.out.println("para class type:" + obj.getClass().getName());
                }
                return null;
            }
        });
        itf.test(1, 2);

        Class cc = MyItf.class;
        Method m = cc.getMethod("test", int.class, int.class);

        if (m.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = m.getAnnotation(MyAnnotation.class);
            System.out.println("annotation:" + annotation.source());
        }
    }
}

interface MyItf {
    @MyAnnotation(source = "mysource")
    String test(int a, int b);
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String source();
}

