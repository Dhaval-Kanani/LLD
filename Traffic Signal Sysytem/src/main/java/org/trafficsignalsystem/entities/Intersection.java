package org.trafficsignalsystem.entities;

import org.trafficsignalsystem.config.SignalTimingConfig;
import org.trafficsignalsystem.enums.Color;
import org.trafficsignalsystem.enums.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Intersection {
    private String intersectionId;
    private Map<Direction, TrafficSignal> signals;
    private SignalTimingConfig signalTimingConfig;
    private ReentrantLock decisionLock = new ReentrantLock();

    public Intersection(String intersectionId, SignalTimingConfig signalTimingConfig) {
        this.intersectionId = intersectionId;
        this.signals = new HashMap<>();
        this.signalTimingConfig = signalTimingConfig;
    }

    public Boolean addSignal(TrafficSignal trafficSignal){
        if(signals.containsKey(trafficSignal.getDirection())){
            return false;
        }
        signals.put(trafficSignal.getDirection(), trafficSignal);
        return true;
    }

    public Boolean isSafeToTurnGreen(Direction direction){
        switch (direction){
            case EAST, WEST -> {
                return isSignalSafeOrMissing(Direction.NORTH) && isSignalSafeOrMissing(Direction.SOUTH);
            }
            case NORTH, SOUTH -> {
                return isSignalSafeOrMissing(Direction.EAST) && isSignalSafeOrMissing(Direction.WEST);
            }
            default -> {
                return false;
            }
        }
    }

    private Boolean isSignalSafeOrMissing(Direction direction) {
        TrafficSignal signal = signals.get(direction);
        return (signal == null) || (signal.getColor() != Color.GREEN);
    }

    public Color getSignalColor(Direction direction){
        if(signals.containsKey(direction)){
            return signals.get(direction).getColor();
        }
        return null;
    }

    public Boolean safeChangeSignalColor(Direction direction, Color color){
        decisionLock.lock();
        try{
            if(color == Color.GREEN){
                if(isSafeToTurnGreen(direction)){
                    changeSignalColor(direction, color);
                    return true;
                } else {
                    System.out.println("Unsafe to change signal to GREEN at intersection: "+ this.intersectionId + " in direction: " + direction);
                    return false;
                }
            } else {
                changeSignalColor(direction, color);
                return true;
            }
        } finally {
            decisionLock.unlock();
        }
    }

    public Boolean changeSignalColor(Direction direction, Color color){
        if(signals.containsKey(direction)){
            TrafficSignal trafficSignal = signals.get(direction);
            trafficSignal.changeColor(color);
            System.out.println("[" + intersectionId + "] " + direction + " signal changed to " + color);
            return true;
        }
        return false;
    }

    public Boolean shouldChangeColor(Direction direction){
        if(signals.containsKey(direction)) {
            TrafficSignal trafficSignal = signals.get(direction);
            long elapsedTime = System.currentTimeMillis() - trafficSignal.getSignalUpdatedOn();
            return elapsedTime >= signalTimingConfig.getDuration(trafficSignal.getColor());
        } else return false;
    }

    public Color getNextColor(Color currentColor) {
        switch (currentColor) {
            case RED -> { return Color.GREEN; }
            case GREEN -> { return Color.YELLOW; }
            case YELLOW -> { return Color.RED; }
            default -> { return Color.RED; } // Safety default
        }
    }

    public String getIntersectionId() {
        return intersectionId;
    }
}
