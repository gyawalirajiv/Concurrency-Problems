package com.rajiv.diningPhilosophers;

import java.util.concurrent.Semaphore;

class DiningPhilosophers {

    private Semaphore[] sem = new Semaphore [] {new Semaphore(1),
            new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1)};
    private Semaphore lockLastOne = new Semaphore(1);


    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int right = philosopher;
        int left  = philosopher == 0 ? 4 : philosopher - 1;

        if(philosopher == 0 || philosopher == 4)
            lockLastOne.acquire();
        sem[left].acquire();


        sem[right].acquire();
        pickLeftFork.run();
        pickRightFork.run();

        eat.run();

        putLeftFork.run();
        putRightFork.run();

        sem[left].release();
        sem[right].release();

        if(philosopher == 0 || philosopher == 4)
            lockLastOne.release();
    }
}