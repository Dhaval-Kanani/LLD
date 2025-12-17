package org.modules.SystemDesign.PubSubModule;

import java.util.LinkedList;
import java.util.Queue;

/*
with the current implementation, because both producer and consumer methods synchronize on the same lock for
the entire critical section, only one thread can access the queue at a time. This means the producer will keep
producing until the queue is full (then wait), and the consumer will keep consuming until the queue is empty (then wait).
They do not truly run in parallel on the queue.
This is a limitation of using a single lock for both operations. However, this approach is necessary to maintain
thread safety for the shared queue. If you want more concurrency, you can use finer-grained locking or use concurrent
data structures like BlockingQueue (as in other implementation), which handle this internally and allow more
parallelism.
For most simple producer-consumer problems, this level of synchronization is acceptable and safe, but it does limit
parallelism. For higher throughput, prefer BlockingQueue or similar concurrent collections.
*/
public class SimplePubSubWithBufferSize {
    private Queue<Integer> queue;
    private final int bufferSize;
    private final Object lock;

    public SimplePubSubWithBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        this.queue = new LinkedList<>();
        this.lock = new Object();
    }

    public void producer(int n){
        try{
            synchronized (lock) {
                while (queue.size() == bufferSize) {
                    System.out.println("Queue is full...");
                    lock.wait();
                }

                queue.offer(n);
                lock.notifyAll();
            }
            System.out.println("Produce: " + (n));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void consumer(){
        try{
            while(true){
                int n;
                synchronized (lock){
                    while(queue.isEmpty()) {
                        System.out.println("Queue is empty...");
                        lock.wait();
                    }
                    n = queue.poll();

                    lock.notifyAll();
                }
                System.out.println("Consume: " + n);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        SimplePubSubWithBufferSize queue = new SimplePubSubWithBufferSize(3);

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
