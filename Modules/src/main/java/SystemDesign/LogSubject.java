package SystemDesign;

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

    public void notifyObservers(){
        for(Map.entry(Map<Integer, List<LogObserver>>) entry: observers)
    }
}
