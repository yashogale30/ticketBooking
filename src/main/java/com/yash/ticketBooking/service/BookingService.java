package com.yash.ticketBooking.service;

import com.yash.ticketBooking.entity.Booking;
import com.yash.ticketBooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> GetAll(){return bookingRepository.findAll();}

    public Boolean SaveEntry(Booking booking){
        try{
            bookingRepository.save(booking);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Booking GetBooking(Long id){
        return bookingRepository.findById(id).orElse(null);
    }

    public void DeleteBooking(Long id){bookingRepository.deleteById(id);}
}



