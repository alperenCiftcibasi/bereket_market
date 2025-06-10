package com.market.Controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.Controller.IUserController;
import com.market.Entities.User;
import com.market.Services.IUserServices;

@RestController
@RequestMapping("/api/users")
public class ImplUserController implements IUserController {

    @Autowired
    private IUserServices userService;

    @Override
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @Override
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getALList();
    }
}