package org.modules.SystemDesign.PubSubModule;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Better than SimplePubSubWithBufferSize bcz of two separate locking condition for producer and consumer
// With a single producer and consumer and controlled timing, both implementations behave similarly.
// However, the ReentrantLock + Condition version scales better, avoids unnecessary wakeups, and matches
// production-grade concurrent queue implementations like ArrayBlockingQueue.

public class ParallelBehavingQueue {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int bufferSize;

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public ParallelBehavingQueue(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void producer(int n) {
        lock.lock();
        try {
            while (queue.size() == bufferSize) {
                System.out.println("Queue is full...");
                notFull.await();
            }
            queue.offer(n);
            System.out.println("Produced: " + n);
            notEmpty.signal(); // wake consumer
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void consumer() {
        while (true) {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    System.out.println("Queue is empty...");
                    notEmpty.await();
                }
                int n = queue.poll();
                System.out.println("Consumed: " + n);
                notFull.signal(); // wake producer
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }

            try {
                Thread.sleep(500); // simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // ---- main method ----
    public static void main(String[] args) {

        ParallelBehavingQueue queue = new ParallelBehavingQueue(3);

        Thread producerThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}
            for (int i = 0; i < 15; i++) {
                queue.producer(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {}
            }
        });

        Thread consumerThread = new Thread(queue::consumer);

        producerThread.start();
        consumerThread.start();
    }
}
