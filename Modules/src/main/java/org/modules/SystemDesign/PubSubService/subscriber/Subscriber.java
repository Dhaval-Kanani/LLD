package org.modules.SystemDesign.PubSubService.subscriber;

import org.modules.SystemDesign.PubSubService.entities.Message;

public interface Subscriber {
    String getId();
    void consumeMessage(Message message);
}
