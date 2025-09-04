package org.modules.SystemDesign.PubSubService;

import org.modules.SystemDesign.PubSubService.entities.Message;
import org.modules.SystemDesign.PubSubService.subscriber.AlertSubscriber;
import org.modules.SystemDesign.PubSubService.subscriber.NewsSubscriber;
import org.modules.SystemDesign.PubSubService.subscriber.Subscriber;

public class PubSubDemo {
    public static void main(String[] args) throws InterruptedException {
        PubSubService pubSubService = PubSubService.getInstance();

        pubSubService.createTopic("SPORTS");
        pubSubService.createTopic("TECH");


        Subscriber Admin = new AlertSubscriber("A001");
        Subscriber sportFan1 = new NewsSubscriber("N001");
        Subscriber sportFan2 = new NewsSubscriber("N004");
        Subscriber Techie = new NewsSubscriber("N002");
        Subscriber GeneralConsumer = new NewsSubscriber("N003");

        pubSubService.subscribeTopic(Admin, "TECH");
        pubSubService.subscribeTopic(sportFan1, "SPORTS");
        pubSubService.subscribeTopic(sportFan2, "SPORTS");
        pubSubService.subscribeTopic(Techie, "TECH");
        pubSubService.subscribeTopic(GeneralConsumer, "SPORTS");
        pubSubService.subscribeTopic(GeneralConsumer, "TECH");

        Message m1 = new Message("Email Alert");
        Message m2 = new Message("FIFA going to host in England");
        Message m3 = new Message("France won the FIFA world cup");
        Message m4 = new Message("Ronaldo Retired!!");


        pubSubService.publishMessage(m1, "TECH");
        pubSubService.publishMessage(m2, "SPORTS");
        pubSubService.publishMessage(m3, "SPORTS");
        pubSubService.publishMessage(m4, "SPORTS");


        pubSubService.setOffSetForSubscriber(sportFan1, 0, "SPORTS");

        Thread.sleep(1000);

    }
}
