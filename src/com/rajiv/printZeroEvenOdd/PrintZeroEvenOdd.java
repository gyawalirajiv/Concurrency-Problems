package com.rajiv.printZeroEvenOdd;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {
}

class ZeroEvenOdd {
    private int n;
    private int lastPrinted;

    Semaphore zeroSemaphore;
    Semaphore oddSemaphore;
    Semaphore evenSemaphore;

    public ZeroEvenOdd(int n) {
        this.n = n;
        lastPrinted = 0;

        zeroSemaphore = new Semaphore(1);
        oddSemaphore = new Semaphore(1);
        evenSemaphore = new Semaphore(1);

        try {
            oddSemaphore.acquire();
            evenSemaphore.acquire();
        } catch (InterruptedException e) {}
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        int numberOfZeros = n;
        while (numberOfZeros-- > 0){
            zeroSemaphore.acquire();
            printNumber.accept(0);
            if(lastPrinted % 2 == 0) oddSemaphore.release();
            else evenSemaphore.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        int numberOfEven = n / 2;
        while (numberOfEven-- > 0){
            evenSemaphore.acquire();
            printNumber.accept(++lastPrinted);
            zeroSemaphore.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        int numberOfOdd = n - n / 2;
        while (numberOfOdd-- > 0){
            oddSemaphore.acquire();
            printNumber.accept(++lastPrinted);
            zeroSemaphore.release();
        }
    }
}