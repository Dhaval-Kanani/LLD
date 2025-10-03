package org.modules.SystemDesign.PubSubModule;

public class Main {
    public static void main(String[] args) {

//         1. Using Blocking Queue
        PubSubModelUsingBlockingQueue pubSubModelUsingBlockingQueue = new PubSubModelUsingBlockingQueue(10);
        pubSubModelUsingBlockingQueue.demo();

        // 2. Simple Pub Sub
//        SimplePubSub simplePubSub = new SimplePubSub();
//        Thread producerThread = new Thread(()->{
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            simplePubSub.publisher(10);
//        });
//        Thread consumerThread = new Thread(()->{
//            simplePubSub.consumer();
//        });
//
//        producerThread.start();
//        consumerThread.start();
        // 3. Simple Pub Sub with buffer size
//
//        SimplePubSubWithBufferSize simplePubSubWithBufferSize = new SimplePubSubWithBufferSize(3);
//        Thread producerThread = new Thread(()->{
//            for(int i=0; i<15; i++){
//                simplePubSubWithBufferSize.producer(i);
//            }
//        });
//
//        Thread consumerThread = new Thread(()->{
//            simplePubSubWithBufferSize.consumer();
//        });
//
//        producerThread.start();
//        consumerThread.start();
    }
}
