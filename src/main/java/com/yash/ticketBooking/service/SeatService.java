package com.yash.ticketBooking.service;

import com.yash.ticketBooking.entity.Seat;
import com.yash.ticketBooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;
    @Autowired private RedisTemplate<String, Object> redisTemplate;


    public List<Seat> GetAll(){return seatRepository.findAll();}

    public Boolean SaveEntry(Seat seat){
        try{
            seatRepository.save(seat);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Seat GetSeat(Long id){
        return seatRepository.findById(id).orElse(null);
    }

    public List<Seat> GetSeatsByEvent(Long eventId){
        return seatRepository.findByEventId(eventId);
    }


    //redis implementation
    private static final long TTL_SECONDS = 60;

    public List<Seat> getSeatsForEvent(Long eventId) {
        String cacheKey = "seats:event:" + eventId;

        List<Seat> cached = (List<Seat>) redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            System.out.println("Cache HIT for " + cacheKey);
            return cached;
        }

        System.out.println("Cache MISS for " + cacheKey + " — querying DB");
        List<Seat> seats = seatRepository.findByEventId(eventId);

        redisTemplate.opsForValue().set(cacheKey, seats, TTL_SECONDS, TimeUnit.SECONDS);
        return seats;
    }

    public void invalidateSeatsCache(Long eventId) {
        String cacheKey = "seats:event:" + eventId;
        redisTemplate.delete(cacheKey);
        System.out.println("Cache INVALIDATED for " + cacheKey);
    }
}