package com.kwk.temp;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThread {
    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
//        executor.setKeepAliveTime(10, TimeUnit.SECONDS);
//        executor.allowCoreThreadTimeOut(true);
        executor.prestartAllCoreThreads();

        for (int i = 0; i < 20; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(new Date());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executor.execute(new ShutDownRunnable(executor));
        executor.awaitTermination(20, TimeUnit.SECONDS);
        System.out.println("main thread exit");
        //Thread.sleep(5000);
    }
}

class ShutDownRunnable implements Runnable{
    private ScheduledThreadPoolExecutor executor;
    public ShutDownRunnable(ScheduledThreadPoolExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void run() {
        executor.shutdown();
    }
}
