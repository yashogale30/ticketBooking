package com.yash.ticketBooking.repository;

import com.yash.ticketBooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
