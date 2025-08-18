package org.trafficsignalsystem.controller;

import org.trafficsignalsystem.config.SignalTimingConfig;
import org.trafficsignalsystem.entities.Intersection;
import org.trafficsignalsystem.entities.TrafficSignal;
import org.trafficsignalsystem.enums.Color;
import org.trafficsignalsystem.enums.Direction;

import java.util.HashMap;
import java.util.Map;

public class TrafficSignalController {
    private Map<String, Intersection> intersections;
    private Boolean isRunning;
    private Thread controlThread;

    public TrafficSignalController() {
        this.intersections = new HashMap<>();
        this.isRunning = false;
    }

    public void start(){
        if(isRunning) return;

        isRunning = true;
        controlThread = new Thread(()->{
            while (isRunning){
                try {
                    controlLoop();
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        controlThread.start();
    }

    public void stop(){
        isRunning = false;
        if(controlThread != null){
            controlThread.interrupt();
        }
    }

    private void controlLoop(){
            for(Intersection intersection: intersections.values()){
                for(Direction direction: Direction.values()){

                    if(intersection.shouldChangeColor(direction)){
                        Color color = intersection.getSignalColor(direction);
                        Color nextColor = intersection.getNextColor(color);

                        // Thread - unsafe operation
//                        if(nextColor == Color.GREEN){
//                            if(intersection.isSafeToTurnGreen(direction)){
//                                intersection.changeSignalColor(direction, nextColor);
//                            } else {
//                                System.out.println("Unsafe to change signal to GREEN at intersection: "+ intersection.getIntersectionId() + " in direction: " + direction);
//                            }
//                        } else{
//                            intersection.changeSignalColor(direction, nextColor);
//                        }

                        // Thread safe operation
                        intersection.safeChangeSignalColor(direction, nextColor);
                    }
                }
            }

    }


    public void addIntersection(String id, SignalTimingConfig signalTimingConfig){
        Intersection intersection = new Intersection(id, signalTimingConfig);
        intersections.put(id, intersection);
    }

    public boolean addSignalToIntersection(String intersectionId, TrafficSignal signal) {
        Intersection intersection = intersections.get(intersectionId);
        if (intersection != null) {
            return intersection.addSignal(signal);
        }
        return false;
    }

    public Intersection getIntersections(String id) {
        return intersections.get(id);
    }
}
