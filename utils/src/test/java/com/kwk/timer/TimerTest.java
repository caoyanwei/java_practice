package com.kwk.timer;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    @Test
    public void test1() throws InterruptedException {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println(new Date());
                    throw new IllegalArgumentException();
                } catch (Throwable throwable) {
                }

            }
        }, 10000, 3000);

        Thread.sleep(10000000);
    }
}
