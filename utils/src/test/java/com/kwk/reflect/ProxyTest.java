package com.kwk.reflect;

import org.junit.Test;

import java.lang.annotation.*;
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

                Annotation[][] annotations = method.getParameterAnnotations();
                for (int j = 0; j < annotations.length; j++) {
                    int length = annotations[j].length;
                    if (length == 0) {
                        System.out.println("没有添加Annotation参数:" + j);
                    } else {
                        for (int k = 0; k < length; k++) {
                            Annotation annotation = annotations[j][k];
                            if (annotation instanceof ArgName) {
                                ArgName argName = (ArgName)annotation;
                                System.out.println("argName.name:" + argName.name());
                            }
                        }
                    }
                }

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
    String test(int a, @ArgName(name = "bbb") int b);
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String source();
}

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@interface ArgName {
    String name();
}

