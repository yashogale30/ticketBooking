package com.yash.ticketBooking.repository;

import com.yash.ticketBooking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
