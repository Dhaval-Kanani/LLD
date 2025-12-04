package org.modules.SystemDesign.SimpleRateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final long maxNoOfRequest;
    private final long intervalLength;
    private Map<String, Window> map = new ConcurrentHashMap<>();

    public RateLimiter(int maxNoOfRequest, int intervalLength){
        this.maxNoOfRequest = maxNoOfRequest;
        this.intervalLength = intervalLength;
    }

    public boolean allow(String key){
        long now = System.currentTimeMillis();
        Window w = map.computeIfAbsent(key, k -> new Window(now));

        synchronized (w){
            if((now - w.start)>=intervalLength){
                w.start = now;
                w.count = 0;
            }
            w.count += 1;
            return w.count <= maxNoOfRequest;
        }
    }

    static class Window{
        long start;
        long count;
        Window(long start){
            this.start = start;
        }
    }
}
