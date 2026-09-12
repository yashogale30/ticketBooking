package com.yash.ticketBooking.controller;

import com.yash.ticketBooking.entity.Seat;
import com.yash.ticketBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Seat> GetAll() {
        return seatService.GetAll();
    }

    @PostMapping
    public Boolean SaveEntry(@RequestBody Seat seat) {
        return seatService.SaveEntry(seat);
    }

    @GetMapping("/{id}")
    public Seat GetSeat(@PathVariable Long id) {
        return seatService.GetSeat(id);
    }

    @GetMapping("/event/{eventId}")
    public List<Seat> GetSeatsByEvent(@PathVariable Long eventId) {
        return seatService.GetSeatsByEvent(eventId);
    }
}