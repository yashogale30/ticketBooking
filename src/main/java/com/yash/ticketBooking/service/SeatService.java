package com.yash.ticketBooking.service;

import com.yash.ticketBooking.entity.Seat;
import com.yash.ticketBooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

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
}
