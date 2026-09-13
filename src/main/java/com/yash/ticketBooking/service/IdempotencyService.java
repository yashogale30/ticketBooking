package com.yash.ticketBooking.service;

import com.yash.ticketBooking.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class IdempotencyService {

    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;

    private  final long TTL_HOURS = 24;

    public  Optional<Booking> getExistingResponse(String idempotencyKey) {
        String cacheKey = "idempotency:" + idempotencyKey;
        Booking existing = (Booking) redisTemplate.opsForValue().get(cacheKey);
        return Optional.ofNullable(existing);
    }

    public  void saveResponse(String idempotencyKey, Booking booking) {
        String cacheKey = "idempotency:" + idempotencyKey;
        redisTemplate.opsForValue().set(cacheKey, booking, TTL_HOURS, TimeUnit.HOURS);
    }
}
