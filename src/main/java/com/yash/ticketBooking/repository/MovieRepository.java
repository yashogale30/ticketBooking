package com.yash.ticketBooking.repository;

import com.yash.ticketBooking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
