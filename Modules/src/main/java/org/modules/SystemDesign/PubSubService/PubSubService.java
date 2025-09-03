package org.modules.SystemDesign.PubSubService;

import org.modules.SystemDesign.PubSubService.entities.Message;
import org.modules.SystemDesign.PubSubService.entities.Topic;
import org.modules.SystemDesign.PubSubService.subscriber.Subscriber;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PubSubService {
    private static PubSubService pubSubService;
    private Map<String, Topic> topicRegistery;

    private PubSubService() {
        if(pubSubService!=null)
            throw new IllegalStateException("Object already created!");
        else{
            this.topicRegistery = new ConcurrentHashMap<>();
        }
    }

    public static PubSubService getInstance(){
        if(pubSubService==null){
            synchronized (PubSubService.class){
                if(pubSubService==null){
                    pubSubService = new PubSubService();
                }
            }
        }
        return pubSubService;
    }

    public void createTopic(String topicId){
        Topic topic = new Topic(topicId);
        topicRegistery.putIfAbsent(topicId, topic);
    }

    public void subscribeTopic(Subscriber subscriber, String topicId){
        Topic topic = topicRegistery.get(topicId);
        if(topic == null){
            System.out.println("Topic not found: " + topicId);
            return;
        }
        topic.addSubscriber(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber, String topicId){
        Topic topic = topicRegistery.get(topicId);
        if(topic == null){
            System.out.println("Topic not found: " + topicId);
            return;
        }
        topic.removeSubscriber(subscriber);
    }

    public void publishMessage(Message message, String topicId){
        Topic topic = topicRegistery.get(topicId);
        if(topic == null){
            System.out.println("Topic not found: " + topicId);
            return;
        }
        topic.publishMessage(message);
    }

}
