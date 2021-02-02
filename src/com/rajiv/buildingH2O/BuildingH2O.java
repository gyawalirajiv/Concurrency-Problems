package com.rajiv.buildingH2O;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class BuildingH2O {

}

class H2O {

    AtomicInteger atomicIntegerH;

    public H2O() {
        atomicIntegerH = new AtomicInteger(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (atomicIntegerH){
            while (atomicIntegerH.get() == 2) { atomicIntegerH.wait(); }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            atomicIntegerH.getAndAdd(1);
            atomicIntegerH.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (atomicIntegerH){
            while (atomicIntegerH.get() != 2) {atomicIntegerH.wait();}
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            atomicIntegerH.addAndGet(-2);
            atomicIntegerH.notifyAll();
        }
    }
}