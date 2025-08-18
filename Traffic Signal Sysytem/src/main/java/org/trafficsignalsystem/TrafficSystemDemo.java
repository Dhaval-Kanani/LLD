package org.trafficsignalsystem;


import org.trafficsignalsystem.config.SignalTimingConfig;
import org.trafficsignalsystem.controller.TrafficSignalController;
import org.trafficsignalsystem.entities.Intersection;
import org.trafficsignalsystem.entities.TrafficSignal;
import org.trafficsignalsystem.enums.Color;
import org.trafficsignalsystem.enums.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TrafficSystemDemo {
    public static void main(String[] args) {
//        trafficSignalDemo();
        System.out.println("🧪 Starting Concurrency Test for Traffic System");
        System.out.println("=".repeat(50));
        testConcurrencySignalChanges();
        System.out.println("All tests completed!");
    }

    public static void trafficSignalDemo(){
        Map<Color, Long> timing = Map.of(
                Color.RED, 5000L,
                Color.GREEN, 3000L,
                Color.YELLOW, 1000L
        );

        SignalTimingConfig signalTimingConfig = new SignalTimingConfig(timing);
        TrafficSignalController controller = new TrafficSignalController();

        controller.addIntersection("main_st", signalTimingConfig);

        controller.addSignalToIntersection("main_st", new TrafficSignal("S01", Direction.NORTH));
        controller.addSignalToIntersection("main_st", new TrafficSignal("S02", Direction.SOUTH));
        controller.addSignalToIntersection("main_st", new TrafficSignal("S03", Direction.EAST));
        controller.addSignalToIntersection("main_st", new TrafficSignal("S04", Direction.WEST));

        controller.start();

        try {
            // Let it run for 30 seconds
            Thread.sleep(30000);

            // Test emergency scenario (future enhancement)
            System.out.println("\n" + "=".repeat(50));
            System.out.println("DEMO: Emergency vehicle approaching!");
            System.out.println("(Emergency handling to be implemented)");

            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Stop the system
            controller.stop();
            System.out.println("Demo completed!");
        }
    }

    public static void testConcurrencySignalChanges() {
        Map<Color, Long> timings = Map.of(
                Color.RED, 5000L,
                Color.GREEN, 3000L,
                Color.YELLOW, 1000L
        );

        SignalTimingConfig signalTimingConfig = new SignalTimingConfig(timings);

        TrafficSignalController controller = new TrafficSignalController();
        controller.addIntersection("main_st", signalTimingConfig);

        controller.addSignalToIntersection("main_st", new TrafficSignal("North", Direction.NORTH));
        controller.addSignalToIntersection("main_st", new TrafficSignal("South", Direction.SOUTH));
        controller.addSignalToIntersection("main_st", new TrafficSignal("East", Direction.EAST));
        controller.addSignalToIntersection("main_st", new TrafficSignal("West", Direction.WEST));

        List<Thread> threads = new ArrayList<>();
        List<Direction> directions = List.of(new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST});
        AtomicBoolean start = new AtomicBoolean(false);

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(4);

        for(int i=0; i<4; i++){
            int finalI = i;
            new Thread(()->{
                try{
                    startLatch.await();
                    Intersection intersection = controller.getIntersections("main_st");
                    //Unsafe way to change color
//                    intersection.changeSignalColor(directions.get(finalI), Color.GREEN);
                    // safe way to change color
                    intersection.safeChangeSignalColor(directions.get(finalI), Color.GREEN);

                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                } finally {
                    finishLatch.countDown();
                }
            }).start();
        }

        startLatch.countDown();
        try{
            finishLatch.await(10, TimeUnit.SECONDS);
            Intersection intersection = controller.getIntersections("main_st");

            System.out.println("Color of direction: "+  Direction.NORTH + " " +
                    intersection.getSignalColor(Direction.NORTH));
            System.out.println("Color of direction: "+  Direction.SOUTH + " " +
                    intersection.getSignalColor(Direction.SOUTH));
            System.out.println("Color of direction: "+  Direction.EAST + " " +
                    intersection.getSignalColor(Direction.EAST));
            System.out.println("Color of direction: "+  Direction.WEST + " " +
                    intersection.getSignalColor(Direction.WEST));

            int greenCount = 0;
            for(Direction direction: Direction.values()){
                Color color = intersection.getSignalColor(direction);
                if(color==Color.GREEN) greenCount++;
            }
            if(greenCount==2){
                System.out.println("Test passed");
            } else System.out.println("Test Failed");
        } catch (InterruptedException e){
            System.err.println("❌ Test interrupted!");
        }
    }
}