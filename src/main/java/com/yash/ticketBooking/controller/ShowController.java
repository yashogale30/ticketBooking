package com.yash.ticketBooking.controller;

import com.yash.ticketBooking.entity.Movie;
import com.yash.ticketBooking.entity.Show;
import com.yash.ticketBooking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @GetMapping
    public List<Show> getALL(){return showService.GetAll();}

    @PostMapping
    public Boolean saveEntry(@RequestBody Show show){return showService.SaveEntry(show);}

    @GetMapping("/{id}")
    public Show getShow(@PathVariable Long id){return showService.GetShow(id);}

}
