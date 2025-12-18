package org.modules.SystemDesign.LoggingFrameWorkv2;

import org.modules.SystemDesign.LoggingFrameWorkv2.Observer.LogObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class LogSubject {
    private Map<Integer, List<LogObserver>> observerMap;

    public LogSubject(){
        this.observerMap = new HashMap<>();
    }

    public void addObserver(int level, LogObserver logObserver){
        List<LogObserver> observerList = observerMap.getOrDefault(level, new ArrayList<>());
        observerList.add(logObserver);
        observerMap.put(level, observerList);
    }

    public void notifyObservers(LogMessage logMessage){
        List<LogObserver> observerList = observerMap.getOrDefault(logMessage.getLevel(), new ArrayList<>());

        for(LogObserver logObserver: observerList){
            CompletableFuture.runAsync(()->{
                logObserver.log(logMessage);
            });
        }
    }
}
