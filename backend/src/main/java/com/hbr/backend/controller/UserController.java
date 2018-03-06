package com.hbr.backend.controller;

import com.hbr.backend.model.User;
import com.hbr.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping(value="/postuser")
    public User postUser(@RequestBody User user) {
        repository.save(new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getContactNumber()));
        return user;
    }

    @GetMapping(value="/user",  produces= MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> users = repository.findAll();
        users.forEach(list::add);
        return list;
    }

    @GetMapping(value="/findbylastname/{lastName}",  produces=MediaType.APPLICATION_JSON_VALUE)
    public List<User> findByLastName(@PathVariable String lastName) {
        List<User> users = repository.findByLastName(lastName);
        return users;
    }

    @DeleteMapping(value="/user/{id}")
    public void deleteUser(@PathVariable long id){
        repository.delete(id);
    }
}