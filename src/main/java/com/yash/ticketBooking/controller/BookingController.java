package com.yash.ticketBooking.controller;

import com.yash.ticketBooking.entity.Booking;
import com.yash.ticketBooking.entity.User;
import com.yash.ticketBooking.repository.UserRepository;
import com.yash.ticketBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Booking> getAll(){return bookingService.GetAll();}

    @PostMapping
    public boolean saveEntry(@RequestBody Booking booking){return bookingService.SaveEntry(booking);} 

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id){bookingService.DeleteBooking(id);} 

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id){return bookingService.GetBooking(id);}

    @PostMapping("/{seatId}")//this is the optimistic locking version
    public ResponseEntity<?> book(@PathVariable Long seatId, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
            Booking booking = bookingService.bookSeat(seatId, user);
            return ResponseEntity.ok(booking);
        } catch (ObjectOptimisticLockingFailureException e) {
            return ResponseEntity.status(409).body("Seat was just booked by someone else, please pick another.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/pessimistic/{seatId}")//this is the pessimistic locking version
    public ResponseEntity<?> bookPessimistic(@PathVariable Long seatId, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
            Booking booking = bookingService.bookSeatPessimistic(seatId, user);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

}
