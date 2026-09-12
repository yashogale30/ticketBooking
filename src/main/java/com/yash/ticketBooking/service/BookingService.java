package com.yash.ticketBooking.service;

import com.yash.ticketBooking.entity.*;
import com.yash.ticketBooking.repository.BookingRepository;
import com.yash.ticketBooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SeatService seatService;

    @Autowired
    private SeatRepository seatRepository;

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


    @Transactional
    public Booking bookSeat(Long seatId, User user) {//this is optimistic locking version
        Seat seat = seatService.GetSeat(seatId);

        if (seat.getStatus() == SeatStatus.BOOKED) {
            throw new RuntimeException("Seat already booked");
        }

        seat.setStatus(SeatStatus.BOOKED);
        seatService.SaveEntry(seat); // this is where the version is checked for optimistic locking
        Booking booking = new Booking();
        booking.setSeat(seat);
        booking.setUser(user);
        booking.setStatus(BookingStatus.CONFIRMED);
        return SaveEntry(booking) ? booking : null;
    }

    @Transactional
    public Booking bookSeatPessimistic(Long seatId, User user) {//this is pessimistic locking version

        Seat seat = seatRepository.findByIdForUpdate(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() == SeatStatus.BOOKED) {
            throw new RuntimeException("Seat already booked");
        }

        seat.setStatus(SeatStatus.BOOKED);
        seatRepository.save(seat);

        Booking booking = new Booking();
        booking.setSeat(seat);
        booking.setUser(user);
        booking.setStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }
}



