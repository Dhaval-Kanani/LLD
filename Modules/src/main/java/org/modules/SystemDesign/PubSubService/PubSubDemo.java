package org.modules.SystemDesign.PubSubService;

import org.modules.SystemDesign.PubSubService.entities.Message;
import org.modules.SystemDesign.PubSubService.subscriber.AlertSubscriber;
import org.modules.SystemDesign.PubSubService.subscriber.NewsSubscriber;
import org.modules.SystemDesign.PubSubService.subscriber.Subscriber;

public class PubSubDemo {
    public static void main(String[] args){
        PubSubService pubSubService = PubSubService.getInstance();

        pubSubService.createTopic("T001");
        pubSubService.createTopic("T002");


        Subscriber s1 = new AlertSubscriber("A001");
        Subscriber s2 = new NewsSubscriber("N001");

        pubSubService.subscribeTopic(s1, "T001");
        pubSubService.subscribeTopic(s2, "T002");

        Message m1 = new Message("Email Alert");
        Message m2 = new Message("Blog published");
        pubSubService.publishMessage(m1, "T001");
        pubSubService.publishMessage(m2, "T002");
    }
}
