import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;

/** 
 * Second approach performs better, because compare to 1st approach, it use waiting queue to avoid
 * unnecessary notify, reduce the lock competition. compare to 3rd and 4th approach, it use
 * synchronized queue to avoid unneccessary CPU execution.
 */

/**
 * Synchronized
 */
class FooBar {
    private int n;
    private int flag = 0;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (flag == 1) {
                    this.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = 1;
                this.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (flag == 0) {
                    this.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = 0;
                this.notifyAll();
            }
        }
    }
}

/**
 * Lock
 */
class FooBarLock {

    private int n;
    private int flag;

    ReentrantLock reentrantLock;
    Condition fooPrinterCondtion;
    Condition barPrinterCondition;

    public FooBarLock(int n) {
        this.n = n;
        this.flag = 0;
        this.reentrantLock = new ReentrantLock();
        this.fooPrinterCondtion = reentrantLock.newCondition();
        this.barPrinterCondition = reentrantLock.newCondition();
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            reentrantLock.lock();
            try {
                while (flag == 1) {
                    barPrinterCondition.await();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = 1;
                fooPrinterCondtion.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            reentrantLock.lock();
            while (flag == 0) {
                fooPrinterCondtion.await();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            flag = 0;
            barPrinterCondition.signalAll();
            reentrantLock.unlock();
        }
    }

}

/**
 * Volatile
 */
class FooBarVolatile {

    private int n;
    private volatile int flag = 0;

    public FooBarVolatile(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (true) {
                if (flag == 0) {
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    flag = 1;
                    break;
                }
                Thread.sleep(1);
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (true) {
                if (flag == 1) {
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    flag = 0;
                    break;
                }
                Thread.sleep(1);
            }
        }
    }
}

/**
 * AtomicInteger
 */
class FooBarCAS {

    private int n;
    private AtomicInteger flag;

    public FooBarCAS(int n) {
        this.n = n;
        this.flag = new AtomicInteger(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!flag.compareAndSet(0, 1)) {
                Thread.sleep(1);
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!flag.compareAndSet(1, 0)) {
                Thread.sleep(1);
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
        }
    }
}