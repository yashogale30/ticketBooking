package com.yash.ticketBooking.controller;

import com.yash.ticketBooking.entity.Booking;
import com.yash.ticketBooking.entity.User;
import com.yash.ticketBooking.repository.UserRepository;
import com.yash.ticketBooking.service.BookingService;
import com.yash.ticketBooking.service.IdempotencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IdempotencyService idempotencyService;

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

    @PostMapping("/pessimistic/{seatId}")
    public ResponseEntity<?> bookPessimistic(
            @PathVariable Long seatId,
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @AuthenticationPrincipal UserDetails userDetails) {

        Optional<Booking> existing = idempotencyService.getExistingResponse(idempotencyKey);
        if (existing.isPresent()) {
            System.out.println("Idempotent replay — returning cached result for key " + idempotencyKey);
            return ResponseEntity.ok(existing.get());
        }

        try {
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
            Booking booking = bookingService.bookSeatPessimistic(seatId, user);
            idempotencyService.saveResponse(idempotencyKey, booking);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

}
