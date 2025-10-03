package org.modules.SystemDesign.PubSubModule;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.random.RandomGenerator;

public class PubSubModelUsingBlockingQueue {
    private BlockingDeque<Integer> queue;
    private ArrayList<String> arr;
    private final Object lock;

    public PubSubModelUsingBlockingQueue(int capacity) {
        this.queue = new LinkedBlockingDeque<>(capacity);
        this.arr = new ArrayList<>();
        this.lock = new Object();
    }

    public void demo(){

        Thread producerThread = new Thread(()->{
            try{
                for(int i=0; i<25; i++){
                    int n = RandomGenerator.getDefault().nextInt(1, 30);
                    queue.put(n);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumerThread1 = new Thread(() ->{
            try{
                while(true){
                    int n = queue.take();
                    String res = fib(n);

                    synchronized (lock){
                        arr.add(res);
                        if(arr.size()==5) {
                            lock.notify();
                            while(!arr.isEmpty()) lock.wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumerThread2 = new Thread(()->{
            try{
                while(true){
                    synchronized (lock){
                        while(arr.size()<5) lock.wait();

                        System.out.println("--- Batch Result start ---");

                        arr.forEach((x)-> {
                            System.out.println(x);
                        });
                        arr.clear();

                        System.out.println("--- Batch Result end ---");
                        lock.notify();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }

    private String fib(int n){
        int f = 0, s = 1;
        for(int i=1; i<n; i++){
            int t = f + s;
            f = s;
            s = t;
        }
        return "Fib of " + n + " is: " + s;
    }
}
