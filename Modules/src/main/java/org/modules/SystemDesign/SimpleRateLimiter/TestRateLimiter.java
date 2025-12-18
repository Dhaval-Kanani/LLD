package org.modules.SystemDesign.SimpleRateLimiter;

public class TestRateLimiter {
    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(3, 3000);

        for(int i=1; i<=5; i++){
            boolean allowed = rateLimiter.allow("user");
            System.out.println("Request: " + i + (allowed ? " allowed" : " not allowed"));
            Thread.sleep(1000);
        }

        System.out.println("Sleeping for 3 second");
        Thread.sleep(3000);

        boolean allowed = rateLimiter.allow("user");
        System.out.println("Request: " + 6 + (allowed ? " allowed" : " not allowed"));
    }
}


