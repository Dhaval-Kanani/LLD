package org.modules.SystemDesign.PubSubService.subscriber;

import org.modules.SystemDesign.PubSubService.entities.Message;

public class AlertSubscriber implements Subscriber {
    private String subscriberId;

    public AlertSubscriber(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    @Override
    public String getId() {
        return subscriberId;
    }

    @Override
    public void consumeMessage(Message message) {
        System.out.println("ALERT: " + message.getMessage());
    }
}
