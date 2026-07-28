import java.util.concurrent.CountDownLatch;

class Foo {
    private final CountDownLatch latch1;
    private final CountDownLatch latch2;

    public Foo() {
        latch1 = new CountDownLatch(1);
        latch2 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        // Signal thread B that first() has finished
        latch1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // Wait until first() finishes
        latch1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        // Signal thread C that second() has finished
        latch2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // Wait until second() finishes
        latch2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}