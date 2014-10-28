package com.kwk.perf;

import org.junit.Test;

public class PerformanceMarkerTest {
    @Test
    public void basicTest() throws InterruptedException {
        PerformanceMarker marker = new PerformanceMarker("testGroup");
        marker.mark("begin");
        Thread.sleep(300);
        marker.mark("aaa");
        Thread.sleep(500);
        marker.mark("bbb");
        Thread.sleep(80);
        marker.mark("ccc");
        Thread.sleep(100);
        marker.mark("ddd");
        Thread.sleep(1000);
        String info = marker.end("end");
        System.out.println(info);
    }
}
