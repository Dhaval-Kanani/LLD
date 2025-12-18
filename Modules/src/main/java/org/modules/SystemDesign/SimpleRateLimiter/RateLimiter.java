package org.modules.SystemDesign.SimpleRateLimiter;

public interface RateLimiter {
    Boolean allow(String key);
}
