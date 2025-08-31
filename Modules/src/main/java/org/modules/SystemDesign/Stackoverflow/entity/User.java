package org.modules.SystemDesign.Stackoverflow.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private String userId;
    private String userName;
    private AtomicInteger reputation;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.reputation = new AtomicInteger(0);
    }

    public synchronized void changeReputation(int change){
        this.reputation.addAndGet(change);
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public AtomicInteger getReputation() {
        return reputation;
    }
}
