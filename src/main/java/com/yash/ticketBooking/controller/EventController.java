package com.yash.ticketBooking.controller;

import com.yash.ticketBooking.entity.Event;
import com.yash.ticketBooking.service.EventService;
import com.yash.ticketBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Event>getALL(){return eventService.GetAll();}

    @PostMapping
    public Boolean saveEntry(@RequestBody Event event){return eventService.SaveEntry(event);} 

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id){return eventService.GetEvent(id);}

    @GetMapping("/{eventId}/seats")//redis part
    public ResponseEntity<?> getSeats(@PathVariable Long eventId) {
        return ResponseEntity.ok(seatService.getSeatsForEvent(eventId));
    }
}
