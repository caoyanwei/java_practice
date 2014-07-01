package com.kwk.spring;

public class BasicServiceImpl implements BasicService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
