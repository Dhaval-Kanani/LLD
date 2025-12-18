package org.modules.SystemDesign.SimpleRateLimiter;

import java.util.Map;

public class WindowRateLimiter implements RateLimiter{
    private long maxNoOfRequest;
    private long intervalLength;
    Map<String, Window> windowMap;

    WindowRateLimiter(long maxNoOfRequest, long intervalLength){
        this.maxNoOfRequest = maxNoOfRequest;
        this.intervalLength = intervalLength;
    }

    @Override
    public Boolean allow(String key){
        Window w;
        long now = System.currentTimeMillis();
        if(!windowMap.containsKey(key)){
            windowMap.put(key, new Window(maxNoOfRequest));
        }
        w = windowMap.get(key);

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
