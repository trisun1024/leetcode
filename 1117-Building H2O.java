import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class H2O {

    Semaphore hydrogenSemaphore;
    Semaphore oxygenSemaphore;
    int count;

    public H2O() {
        count = 0;
        hydrogenSemaphore = new Semaphore(2);
        oxygenSemaphore = new Semaphore(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        count++;
        if (count == 2)
            oxygenSemaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSemaphore.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        count = 0;
        hydrogenSemaphore.release();
        hydrogenSemaphore.release();
    }
}

class H2OLock {
    private volatile boolean flag = true;
    private ReentrantLock lock = new ReentrantLock();
    private Condition hcondition = lock.newCondition();
    private Condition ocondition = lock.newCondition();
    private int i = 0;

    public H2OLock() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        try {
            lock.lock();
            while (!flag) {
                hcondition.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            i++;
            if (i % 2 == 0) {
                flag = false;
                ocondition.signalAll();
            }
        } finally {
            lock.unlock();
        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        try {
            lock.lock();
            while (flag) {
                ocondition.await();
            }
            // releaseOxygen.run() outputs "H". Do not change or remove this line.
            releaseOxygen.run();
            flag = true;
            hcondition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}