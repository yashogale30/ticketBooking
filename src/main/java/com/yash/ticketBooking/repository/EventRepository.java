package com.yash.ticketBooking.repository;

import com.yash.ticketBooking.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
