import java.util.concurrent.atomic.AtomicInteger;

class Foo {

    private AtomicInteger firstJobDone;
    private AtomicInteger secondJobDone;

    public Foo() {
        firstJobDone = new AtomicInteger(0);
        secondJobDone = new AtomicInteger(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJobDone.get() != 1) {
            // wait();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJobDone.get() != 1) {
            // wait();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}