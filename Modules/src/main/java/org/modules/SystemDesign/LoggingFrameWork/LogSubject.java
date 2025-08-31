package org.modules.SystemDesign.LoggingFrameWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogSubject {
    Map<Integer, List<LogObserver>> observers;

    public LogSubject() {
        this.observers = new HashMap<>();
    }

    public void addObserver(int level, LogObserver logObserver){
        List<LogObserver> logObservers = observers.getOrDefault(level, new ArrayList<>());
        logObservers.add(logObserver);
        observers.put(level, logObservers);
    }

    public void notifyObservers(int level, String msg){
        List<LogObserver> observersList = observers.getOrDefault(level, new ArrayList<>());

        observersList.forEach(observer -> observer.log(msg));
    }
}
