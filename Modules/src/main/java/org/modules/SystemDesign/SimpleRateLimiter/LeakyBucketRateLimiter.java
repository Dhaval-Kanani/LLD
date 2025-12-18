package org.modules.SystemDesign.SimpleRateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class LeakyBucketRateLimiter implements RateLimiter{
    private long maxNoOfRequest;
    private Map<String, BlockingDeque<Long>> keyMap;

    LeakyBucketRateLimiter(long maxNoOfRequest){
        this.maxNoOfRequest = maxNoOfRequest;
        this.keyMap = new HashMap<>();
    }

    @Override
    public Boolean allow(String key) {
        if(!keyMap.containsKey(key)){
            keyMap.put(key, new LinkedBlockingDeque<>((int) maxNoOfRequest));
        }
        BlockingDeque<Long> queue = keyMap.get(key);
        synchronized (queue){
            if(queue.size()==maxNoOfRequest){
                return false;
            } else{
                queue.offer(1L);
                return true;
            }
        }
    }

    public Boolean consume(String key){
        BlockingDeque<Long> queue = keyMap.getOrDefault(key, new LinkedBlockingDeque<>((int) maxNoOfRequest));
        if(!queue.isEmpty()){
            queue.poll();
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        LeakyBucketRateLimiter leakyBucketRateLimiter = new LeakyBucketRateLimiter(3);
        String userkey = "U001";
        AtomicInteger allowedCount = new AtomicInteger();
        AtomicInteger notAllowedCount = new AtomicInteger();
        AtomicInteger consumedCount = new AtomicInteger();
        AtomicInteger notConsumedCount = new AtomicInteger();
        Thread requestThread = new Thread(()->{
            for(int i=0; i<15; i++){
                Boolean isallowed = leakyBucketRateLimiter.allow(userkey);
                if(isallowed) allowedCount.incrementAndGet();
                else notAllowedCount.incrementAndGet();

                System.out.println("Request " + (i+1) + (isallowed ? " allowed" : " not allowed"));
                try{
                    Thread.sleep(100);
                } catch (InterruptedException ignore){}
            }
        });

        Thread consumerThread = new Thread(()->{
            try{
                Thread.sleep(100);
            } catch (InterruptedException ignore){}
            for(int i=1; i<=15; i++){
                Boolean isConsumed = leakyBucketRateLimiter.consume(userkey);
                if(isConsumed) consumedCount.incrementAndGet();
                else notConsumedCount.incrementAndGet();
                System.out.println("Request " + i + (isConsumed ? " consumed" : " not consumed"));
                try{
                    Thread.sleep(200);
                } catch (InterruptedException ignore){}
            }
        });

        requestThread.start();
        consumerThread.start();

        requestThread.join();
        consumerThread.join();

        System.out.println("Allowed: " + allowedCount + " Not Allowed: " + notAllowedCount);
        System.out.println("Allowed: " + consumedCount + " Not Allowed: " + notConsumedCount);
    }

}
