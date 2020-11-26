import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;

    private ReentrantLock lock = new ReentrantLock();
    private volatile int active;
    private volatile int count;
    private Condition zeroCondition;
    private Condition evenCondition;
    private Condition oddCondition;

    public ZeroEvenOdd(int n) {
        this.n = n;
        active = 0;
        count = 0;
        zeroCondition = lock.newCondition();
        evenCondition = lock.newCondition();
        oddCondition = lock.newCondition();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            lock.lock();
            try {
                if (active != 0) {
                    zeroCondition.await();
                }
                printNumber.accept(0);
                count++;
                if (count % 2 == 1) {
                    active = 1;
                    oddCondition.signalAll();
                } else {
                    active = 2;
                    evenCondition.signalAll();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            lock.lock();
            try {
                if (active != 2) {
                    evenCondition.await();
                }
                printNumber.accept(count);
                active = 0;
                zeroCondition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            lock.lock();
            try {
                if (active != 1) {
                    oddCondition.await();
                }
                printNumber.accept(count);
                active = 0;
                zeroCondition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

}