package com.yash.ticketBooking.repository;

import com.yash.ticketBooking.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
