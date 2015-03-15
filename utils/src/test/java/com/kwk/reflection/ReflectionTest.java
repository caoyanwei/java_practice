package com.kwk.reflection;

import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class ReflectionTest {

    @Test
    public void filedTest() throws NoSuchFieldException, IllegalAccessException {
        Person p = new Person(12, "aaa");
        Class personClass = Person.class;

        //private变量
        Field nameField = personClass.getDeclaredField("name");
        System.out.println(nameField);
        nameField.setAccessible(true);
        nameField.set(p, "bbb");

        //public变量
        Field field = personClass.getField("age");
        Assert.assertTrue(field != null);
        System.out.println(field.get(p));

    }
}
