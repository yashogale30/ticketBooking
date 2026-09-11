package com.yash.ticketBooking.controller;

import com.yash.ticketBooking.entity.Booking;
import com.yash.ticketBooking.entity.User;
import com.yash.ticketBooking.service.BookingService;
import com.yash.ticketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAll(){return bookingService.GetAll();}

    @PostMapping
    public boolean saveEntry(@RequestBody Booking booking){return bookingService.SaveEntry(booking);}

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id){bookingService.DeleteBooking(id);}

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id){return bookingService.GetBooking(id);}

}



