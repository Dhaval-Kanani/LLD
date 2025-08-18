package org.trafficsignalsystem.entities;

import org.trafficsignalsystem.config.SignalTimingConfig;
import org.trafficsignalsystem.enums.Color;
import org.trafficsignalsystem.enums.Direction;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TrafficSignal {
    private String trafficSignalId;
    private Color color;
    private long signalUpdatedOn;
    private Direction direction;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public TrafficSignal(String trafficSignalId, Direction direction) {
        this.trafficSignalId = trafficSignalId;
        this.color = Color.RED;
        this.direction = direction;
        this.signalUpdatedOn = System.currentTimeMillis();
    }

    public void changeColor(Color color) {
        lock.writeLock().lock();
        try {
            this.color = color;
            this.signalUpdatedOn = System.currentTimeMillis();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String getTrafficSignalId(){
        return trafficSignalId;
    }

    public Color getColor(){
        lock.readLock().lock();
        try {
            return this.color;
        } finally {
            lock.readLock().unlock();
        }
    }

    public Direction getDirection(){
        return this.direction;
    }

    public long getSignalUpdatedOn() {
        lock.readLock().lock();
        try {
            return signalUpdatedOn;
        } finally {
            lock.readLock().unlock();
        }
    }
}
