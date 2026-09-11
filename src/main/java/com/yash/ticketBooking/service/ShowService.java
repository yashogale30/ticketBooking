package com.yash.ticketBooking.service;

import com.yash.ticketBooking.entity.Show;
import com.yash.ticketBooking.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    public List<Show> GetAll(){return showRepository.findAll();}

    public Boolean SaveEntry(Show show){
        try{
            showRepository.save(show);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Show GetShow(Long id){
        return showRepository.findById(id).orElse(null);
    }
}


