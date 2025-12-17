package org.modules.SystemDesign.PubSubModule;

import java.util.LinkedList;
import java.util.Queue;

public class SimplePubSub {
    private Queue<Integer> queue;

    public SimplePubSub() {
        this.queue = new LinkedList<>();
    }

    public synchronized void publisher(int n){
        System.out.println("Published: " + n);
        queue.offer(n);
        notifyAll();
    }

    public synchronized void consumer(){
        while(queue.isEmpty()){
            try{
                System.out.println("Waiting...");
                wait();
            } catch (InterruptedException e){

            }
        }
        System.out.println("Consuming...");
        int n = queue.poll();
        System.out.println(n);
        System.out.println("Consumed...");
    }

    public static void main(String[] args) {

        SimplePubSub simplePubSub = new SimplePubSub();
        Thread producerThread = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            simplePubSub.publisher(10);
        });
        Thread consumerThread = new Thread(()->{
            simplePubSub.consumer();
        });

        producerThread.start();
        consumerThread.start();
    }
}
