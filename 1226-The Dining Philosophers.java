import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

class DiningPhilosophers {

    private Semaphore[] sems;

    public DiningPhilosophers() {
        this.sems = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            this.sems[i] = new Semaphore(1);
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat,
            Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
        Semaphore left = sems[philosopher];
        Semaphore right = philosopher == 0 ? sems[4] : sems[philosopher - 1];
        if (philosopher % 2 == 0) {
            left.acquire();
            pickLeftFork.run();
            right.acquire();
            pickRightFork.run();
        } else {
            right.acquire();
            pickRightFork.run();
            left.acquire();
            pickLeftFork.run();
        }
        eat.run();
        if (philosopher % 2 == 0) {
            putRightFork.run();
            right.release();
            putLeftFork.run();
            left.release();
        } else {
            putLeftFork.run();
            left.release();
            putRightFork.run();
            right.release();
        }
    }
}

class DiningPhilosophersI {
    private Lock[] locks;

    public DiningPhilosophersI() {
        locks = new Lock[5];
        for (int i = 0; i < 5; i++) {
            locks[i] = new ReentrantLock();
        }

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat,
            Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
        int current = philosopher;
        int next = (philosopher + 1) % 5;

        synchronized (locks) {
            locks[current].lock();
            locks[next].lock();
        }
        try {
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
        } finally {
            locks[current].unlock();
            locks[next].unlock();
        }

    }
}