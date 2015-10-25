package com.kwk.runtime;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

public class RunTimePathTest {
    @Test
    public void runtimePathTest() throws URISyntaxException {
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        File file = new File(path);
        System.out.println(file.getPath());

        System.out.println(file.getParent());
    }
}
