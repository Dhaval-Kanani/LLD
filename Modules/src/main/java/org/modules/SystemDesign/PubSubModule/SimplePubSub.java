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
}
