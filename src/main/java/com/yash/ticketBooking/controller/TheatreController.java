package com.yash.ticketBooking.controller;

import com.yash.ticketBooking.entity.Theatre;
import com.yash.ticketBooking.service.TheatreService;
import com.yash.ticketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @GetMapping
    public List<Theatre>getAll(){return theatreService.GetAll();}

    @PostMapping
    public Boolean saveEntry(@RequestBody Theatre theatre){return theatreService.SaveEntry(theatre);}

    @GetMapping("/{id}")
    public Theatre getTheatre(@PathVariable Long id){return theatreService.GetTheatre(id);}
}
