package com.kwk.aspectj;

public class Person {
    private int age;
    public Person(int age) {
        this.age = age;
    }

    public void update() {
        ++age;
    }
}
