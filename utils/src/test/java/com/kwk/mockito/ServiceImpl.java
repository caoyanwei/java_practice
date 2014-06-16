package com.kwk.mockito;


public class ServiceImpl {
    private int base;

    private InvokedItf itf;

    public ServiceImpl(int base) {
        this.base = base;
    }

    public int myMethod(int a, int b) {
        int result = base;
        result += itf.add(a, b);
        return result;
    }
}
