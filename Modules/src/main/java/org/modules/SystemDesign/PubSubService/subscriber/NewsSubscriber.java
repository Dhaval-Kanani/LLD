package org.modules.SystemDesign.PubSubService.subscriber;

import org.modules.SystemDesign.PubSubService.entities.Message;

public class NewsSubscriber implements Subscriber{
    private String subscriberId;

    public NewsSubscriber(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    @Override
    public String getId() {
        return subscriberId;
    }

    @Override
    public void consumeMessage(Message message) {
        System.out.println("NEWS: " + message.getMessage());
    }
}
