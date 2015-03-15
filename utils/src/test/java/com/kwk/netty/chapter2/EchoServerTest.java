package com.kwk.netty.chapter2;


import org.junit.Test;

public class EchoServerTest {
    @Test
    public void basicTest() throws Exception {
        EchoServer server = new EchoServer(998);
        server.start();
    }
}
