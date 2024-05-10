package com.example.java.database.practice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping(
            path = "/createuser"
    )
    public boolean createUser(@RequestBody User user) {
        userRepository.save(user);
        return true;
    }

    @GetMapping(
            path = "/allusers"
    )
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @GetMapping(
            path = "/users/{id}"
    )
    public User userById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return null;
        }else{
            return user.get();
        }
    }

    @DeleteMapping(
            path = "/delete-user/{id}"
    )
    public boolean deleteUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return false;
        }else{
            userRepository.delete(user.get());
            return true;
        }
    }

    @PutMapping(
            path = "/update-user/{id}"
    )
    public boolean updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> originalUser = userRepository.findById(id);
        if(originalUser.isEmpty()) {
            return false;
        }else{
            originalUser.get().setName(user.getName());
            userRepository.save(originalUser.get());
            return true;
        }
    }
}

