package org.modules.SystemDesign.SimpleRateLimiter;


import java.util.HashMap;
import java.util.Map;


public class TokenBucketRateLimiter implements RateLimiter{
    private long maxNoOfRequest;
    private double refillRate;
    Map<String, Window> windowMap;

    public TokenBucketRateLimiter(long maxNoOfRequest, long intervalLength){
        this.maxNoOfRequest = maxNoOfRequest;
        this.refillRate = (double)maxNoOfRequest/(double)intervalLength;
        this.windowMap = new HashMap<>();
    }

    @Override
    public Boolean allow(String key){
        Window w;
        long now = System.currentTimeMillis();
        if(!windowMap.containsKey(key)){
            windowMap.put(key, new Window((double) maxNoOfRequest));
        }
        w = windowMap.get(key);

        synchronized (w){
            refill(w, now);
            if(w.tokens >= 1){
                w.tokens--;
                return true;
            }
            return false;
        }
    }

    private void refill(Window w, long now){
        long elapsedTime = now - w.lastrefill;
        w.tokens = Math.min(maxNoOfRequest, w.tokens + (refillRate/1000.0)*elapsedTime);
        w.lastrefill = now;
    }

    static class Window{
        private double tokens;
        private long lastrefill;
        Window(double tokens){
            this.tokens = tokens;
            this.lastrefill = System.currentTimeMillis();
        }
    }
}
