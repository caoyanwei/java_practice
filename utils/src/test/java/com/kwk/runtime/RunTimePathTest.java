package com.kwk.runtime;

import org.junit.Test;

import java.net.URISyntaxException;

public class RunTimePathTest {
    @Test
    public void runtimePathTest() throws URISyntaxException {
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        System.out.println(path);
    }
}
