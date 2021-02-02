package com.rajiv.printFooBarAlternately;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintFooBarAlternately {

    public static void main(String[] args) {

        FooBar fooBar = new FooBar(2);

        Thread thread1 = new Thread();
        Thread thread2 = new Thread();
        try {
            fooBar.foo(thread1);
            fooBar.bar(thread2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class FooBar {
    private int n;

    CountDownLatch[] countDownLatchFoo;
    CountDownLatch[] countDownLatchBar;

    public FooBar(int n) {
        this.n = n;

        countDownLatchFoo = new CountDownLatch[n];
        countDownLatchBar = new CountDownLatch[n];
        for (int i = 0; i < n; i++) {
            countDownLatchFoo[i] = new CountDownLatch(1);
            countDownLatchBar[i] = new CountDownLatch(1);
        }
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            if(i > 0){
                countDownLatchBar[i - 1].await();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            System.out.println("foo" + i);
            countDownLatchFoo[i].countDown();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            countDownLatchFoo[i].await();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            System.out.println("bar" + i);
            countDownLatchBar[i].countDown();
        }
    }
}