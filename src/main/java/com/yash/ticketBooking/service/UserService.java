package com.yash.ticketBooking.service;
import com.yash.ticketBooking.entity.User;
import com.yash.ticketBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> GetAll(){ return userRepository.findAll(); };

    public boolean SaveEntry(User user){
        try{
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteUser(Long id){userRepository.deleteById(id);}

    public User GetUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

}
