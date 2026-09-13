package com.yash.ticketBooking;

import com.yash.ticketBooking.entity.Event;
import com.yash.ticketBooking.entity.Seat;
import com.yash.ticketBooking.entity.SeatStatus;
import com.yash.ticketBooking.repository.EventRepository;
import com.yash.ticketBooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private EventRepository eventRepository;
    @Autowired private SeatRepository seatRepository;

    @Override
    public void run(String... args) {
        if (eventRepository.count() == 0) {
            Event event = new Event();
            event.setTitle("Test Concert");
            event.setDuration("2h");
            eventRepository.save(event);

            for (String seatNum : List.of("1", "2", "3", "4", "5")) {
                Seat seat = new Seat();
                seat.setSeatNumber(seatNum);
                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setEvent(event);
                seatRepository.save(seat);
            }
            System.out.println("Seeded 1 event with 5 seats.");
        }
    }
}
