package com.yash.ticketBooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RateLimiterService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final int MAX_REQUESTS = 5;
    private static final long WINDOW_SECONDS = 60;

    public boolean isAllowed(String userId) {
        String key = "rate:user:" + userId;

        Long currentCount = redisTemplate.opsForValue().increment(key);

        if (currentCount != null && currentCount == 1L) {
            redisTemplate.expire(key, WINDOW_SECONDS, TimeUnit.SECONDS);
        }

        return currentCount != null && currentCount <= MAX_REQUESTS;
    }
}
