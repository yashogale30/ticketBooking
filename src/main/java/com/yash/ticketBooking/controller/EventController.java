package com.yash.ticketBooking.controller;

import com.yash.ticketBooking.entity.Event;
import com.yash.ticketBooking.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event>getALL(){return eventService.GetAll();}

    @PostMapping
    public Boolean saveEntry(@RequestBody Event event){return eventService.SaveEntry(event);} 

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id){return eventService.GetEvent(id);} 
}
