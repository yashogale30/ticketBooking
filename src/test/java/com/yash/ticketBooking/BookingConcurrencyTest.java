package com.yash.ticketBooking;

import com.yash.ticketBooking.entity.Seat;
import com.yash.ticketBooking.entity.SeatStatus;
import com.yash.ticketBooking.entity.User;
import com.yash.ticketBooking.repository.SeatRepository;
import com.yash.ticketBooking.repository.UserRepository;
import com.yash.ticketBooking.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.*;

@SpringBootTest
public class BookingConcurrencyTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testConcurrentBooking() throws InterruptedException {
        //Set up a known seat and user before the race
        Seat seat = new Seat();
        seat.setSeatNumber("A1");
        seat.setStatus(SeatStatus.AVAILABLE);
        seat = seatRepository.save(seat);
        Long seatId = seat.getId();

        User testUser = userRepository.findByEmail("a@a.com")
                .orElseThrow();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);

        Runnable bookingTask = () -> {
            try {
                bookingService.bookSeatPessimistic(seatId, testUser);
                System.out.println("Booking succeeded");
            } catch (Exception e) {
                System.out.println("Booking failed: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        };

        executor.submit(bookingTask);
        executor.submit(bookingTask);
        latch.await();
        executor.shutdown();
    }
}
