package com.yash.ticketBooking.service;

import com.yash.ticketBooking.entity.Event;
import com.yash.ticketBooking.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event>GetAll(){return eventRepository.findAll();}

    public Boolean SaveEntry(Event event){
        try{
            eventRepository.save(event);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Event GetEvent(Long id){
        return eventRepository.findById(id).orElse(null);
    }

}
