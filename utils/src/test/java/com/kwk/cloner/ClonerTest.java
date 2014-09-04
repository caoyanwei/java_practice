package com.kwk.cloner;

import com.rits.cloning.Cloner;
import org.junit.Assert;
import org.junit.Test;

public class ClonerTest {

    @Test
    public void basicTest() {
        Cloner cloner = new Cloner();

        MyPojo pojo = new MyPojo();
        pojo.setAge(10);
        pojo.setName("aaaaa");

        Address address = new Address();
        address.setCity("sh");
        address.setStreet("浦东大道");
        pojo.setAddress(address);

        MyPojo clone = cloner.deepClone(pojo);
        Assert.assertFalse(pojo.getAddress() == clone.getAddress());
    }
}

class MyPojo {
    private String name;
    private int age;
    private Address address;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    Address getAddress() {
        return address;
    }

    void setAddress(Address address) {
        this.address = address;
    }
}

class Address {
    private String city;
    private String street;

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    String getStreet() {
        return street;
    }

    void setStreet(String street) {
        this.street = street;
    }
}
