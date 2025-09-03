package org.modules.SystemDesign.PubSubService.entities;

import org.modules.SystemDesign.PubSubService.subscriber.Subscriber;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class Topic {
    private String topicId;
    private Set<Subscriber> subscribers;
    private ExecutorService executorService;

    public Topic(String topicId, ExecutorService executorService) {
        this.topicId = topicId;
        this.subscribers = new CopyOnWriteArraySet<>();
        this.executorService = executorService;
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
        for(Subscriber subscriber: subscribers){
            executorService.submit(() ->{
                try {
                    subscriber.consumeMessage(message);
                } catch (Exception e){
                    System.out.println("Error while publishing message to subscriber: " + subscriber.getId());
                }
            });
        }
        System.out.println("Published the message successfully to subscribers");
    }
}
