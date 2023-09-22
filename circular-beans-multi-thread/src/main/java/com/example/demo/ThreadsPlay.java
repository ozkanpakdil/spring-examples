package com.example.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsPlay {
    public synchronized void func1() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("func1,2 secs");
    }

    public synchronized void func2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("func2,2sec");
    }

    public synchronized void func3() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("func3,3 secs");
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        ThreadsPlay tp = new ThreadsPlay();

        Thread t1 = new Thread(() -> {
            tp.func1();
        });
        Thread t2 = new Thread(() -> {
            tp.func2();
        });
        Thread t3 = new Thread(() -> {
            tp.func3();
        });
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        final long createdMillis = System.currentTimeMillis();
        executorService.submit(t1);
        executorService.submit(t2);
        executorService.submit(t3);

        long nowMillis = System.currentTimeMillis();
        executorService.shutdown();
        int i = (int) ((nowMillis - createdMillis) / 1000);
        System.out.println("after all thread i:" + i);
    }
}
