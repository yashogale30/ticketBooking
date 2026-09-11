package com.yash.ticketBooking.service;

import com.yash.ticketBooking.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yash.ticketBooking.repository.MovieRepository;

import java.util.List;


@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie>GetAll(){return movieRepository.findAll();}

    public Boolean SaveEntry(Movie movie){
        try{
            movieRepository.save(movie);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Movie GetMovie(Long id){
        return movieRepository.findById(id).orElse(null);
    }

}
