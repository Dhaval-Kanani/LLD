package org.modules.SystemDesign.PubSubService.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Message {
    private String content;
    private LocalDateTime createdOn;

    public Message(String content) {
        this.content = content;
        this.createdOn = LocalDateTime.now();
    }

    public String getMessage(){
        return content + " on " + createdOn;
    }
}
