package com.yash.ticketBooking.service;

import com.yash.ticketBooking.entity.Theatre;
import com.yash.ticketBooking.entity.User;
import com.yash.ticketBooking.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public List<Theatre> GetAll(){return theatreRepository.findAll();}

    public Boolean SaveEntry(Theatre theatre){
        try{
            theatreRepository.save(theatre);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Theatre GetTheatre(Long id){
        return theatreRepository.findById(id).orElse(null);
    }

}
