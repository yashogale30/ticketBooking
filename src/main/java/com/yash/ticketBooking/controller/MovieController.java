package com.yash.ticketBooking.controller;

import com.yash.ticketBooking.entity.Movie;
import com.yash.ticketBooking.service.MovieService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie>getALL(){return movieService.GetAll();}

    @PostMapping
    public Boolean saveEntry(@RequestBody Movie movie){return movieService.SaveEntry(movie);}

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id){return movieService.GetMovie(id);}
}
