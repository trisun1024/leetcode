import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class BoundedBlockingQueue {

    private final int CAPACITY;
    ReentrantLock lock;
    Condition fullCondition;
    Condition emptyCondition;
    private int[] queue;
    private int head;
    private int tail;
    private volatile int size;

    public BoundedBlockingQueue(int capacity) {
        this.CAPACITY = capacity;
        this.lock = new ReentrantLock();
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
        this.queue = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (size == queue.length) {
                fullCondition.await();
            }
            queue[tail++] = element;
            tail %= queue.length;
            size++;
            emptyCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        int element = -1;
        lock.lock();
        try {
            while (size == 0) {
                emptyCondition.await();
            }
            element = queue[head++];
            head %= queue.length;
            size--;
            fullCondition.signalAll();
            return element;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return element;
    }

    public int size() throws InterruptedException {
        lock.lock();
        try {
            return this.size;
        } finally {
            lock.unlock();
        }
    }

}

class BoundedBlockingQueueI {
    private final int[] queue;
    private volatile int size = 0;
    private int head = 0;
    private int tail = 0;

    public BoundedBlockingQueueI(int capacity) {
        this.queue = new int[capacity];
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized (this) {
            while (size == queue.length) {
                wait();
            }
            queue[tail++] = element;
            size++;
            head %= queue.length;
            notifyAll();
        }
    }

    public int dequeue() throws InterruptedException {
        synchronized (this) {
            while (size == 0) {
                wait();
            }
            int res = queue[head++];
            size--;
            head %= queue.length;
            notifyAll();
            return res;
        }
    }

    public int size() {
        return size;
    }
}