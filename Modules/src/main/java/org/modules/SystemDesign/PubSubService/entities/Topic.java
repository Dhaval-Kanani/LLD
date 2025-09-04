package org.modules.SystemDesign.PubSubService.entities;

import org.modules.SystemDesign.PubSubService.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.*;

public class Topic {
    private String topicId;
    private Set<Subscriber> subscribers;
    private CopyOnWriteArrayList<Message> messages;

    public Topic(String topicId) {
        this.topicId = topicId;
        this.subscribers = new CopyOnWriteArraySet<>();
        this.messages = new CopyOnWriteArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
        System.out.println("Subscribed the subscriber " + subscriber.getId() + " to the topic" + topicId);
    }

    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
        System.out.println("Unsubscribed the subscriber " + subscriber.getId() + " to the topic" + topicId);
    }

    public void publishMessage(Message message){
        messages.add(message);

        for(Subscriber subscriber: subscribers){

            CompletableFuture.runAsync(()->{
                subscriber.consumeMessage(message);
            }).exceptionally(r -> {
                System.out.println("Error while publishing message to subscriber: " + subscriber.getId());
                return null;
            }).thenAccept(r -> {
                System.out.println("Consumed!!!");
            });

        }

        System.out.println("Published the message successfully to subscribers");

//-------Use this when you want to show the result of subscriber.consumeMessage(message); in console
//       bcz runAsync won't block main thread. So it's possible that main thread will close before
//       the thread of Fork pool thread
//        List<CompletableFuture<Void>> futures = new ArrayList<>();
//        for(Subscriber subscriber: subscribers){
//
//            CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
//                subscriber.consumeMessage(message);
//            }).exceptionally(r -> {
//                System.out.println("Error while publishing message to subscriber: " + subscriber.getId());
//                return null;
//            }).thenAccept(r -> {
//                System.out.println("Consumed!!!");
//            });
//
//            futures.add(future);
//        }
//
//        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
//        allFutures.thenRun(() ->System.out.println("Published the message successfully to subscribers"));
//        allFutures.join();
    }

    public void offSetSubscriber(Subscriber subscriber, int offset){
        Optional<Subscriber> sub = subscribers.stream()
                .filter(s-> s.getId().equals(subscriber.getId()))
                .findFirst();

        if(sub.isEmpty()){
            System.out.println("Subscriber not found with id: " + subscriber.getId());
            return;
        }

        for(int i=offset; i<messages.size(); i++){
            int finalI = i;
            CompletableFuture.runAsync(()->{
                subscriber.consumeMessage(messages.get(finalI));
            }).exceptionally(r -> {
                System.out.println("Error while publishing message to subscriber: " + subscriber.getId());
                return null;
            }).thenAccept(r -> {
                System.out.println("Consumed!!");
            });
        }

        System.out.println("Changed the offset of the subscriber of id: " + subscriber.getId() + " and published the messages");

    }
}
