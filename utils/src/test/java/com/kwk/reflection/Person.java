package com.kwk.reflection;

public class Person {
    public int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String changeName(String newName) {
        String tmp = this.name;
        this.name = newName;
        return tmp;
    }
}
