package com.yash.ticketBooking.controller;
import com.yash.ticketBooking.entity.User;
import com.yash.ticketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User>getAll(){return userService.GetAll();}

    @PostMapping
    public boolean saveEntry(@RequestBody User user){return userService.SaveEntry(user);}

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){userService.DeleteUser(id);}

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){return userService.GetUser(id);}


}
