package com.rajiv.printInOrder;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintInOrder {

    class Foo {

        AtomicInteger atomicInteger1 = new AtomicInteger(0);
        AtomicInteger atomicInteger2 = new AtomicInteger(0);

        public Foo() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            atomicInteger1.incrementAndGet();
        }

        public void second(Runnable printSecond) throws InterruptedException {

            while (atomicInteger1.get() != 1){}
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            atomicInteger2.incrementAndGet();
        }

        public void third(Runnable printThird) throws InterruptedException {

            while (atomicInteger2.get() != 1){}
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
