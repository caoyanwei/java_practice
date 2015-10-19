package com.kwk.exception;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;

public class ExceptionLog {

    @Test
    public void test1() {
        try {
            aaa();
        } catch (Throwable e) {
            String error = ExceptionUtils.getStackTrace(e);
            System.out.println(error);
        }

    }

    public void aaa() {
        throw new IllegalArgumentException("aaaa");
    }
}
