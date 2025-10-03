package org.modules.SystemDesign.PubSubModule;

import java.util.LinkedList;
import java.util.Queue;

public class SimplePubSub {
    private Boolean isAvailable;
    private Queue<Integer> queue;

    public SimplePubSub() {
        this.isAvailable = false;
        this.queue = new LinkedList<>();
    }

    public synchronized void publisher(int n){
        System.out.println("Published: " + n);
        queue.offer(n);
        isAvailable = true;
        notify();
    }

    public synchronized void consumer(){
        while(!isAvailable){
            try{
                System.out.println("Waiting...");
                wait();
            } catch (InterruptedException e){

            }
        }
        System.out.println("Consuming...");
        int n = queue.poll();
        isAvailable = false;
        System.out.println(n);
        System.out.println("Consumed...");
    }
}
