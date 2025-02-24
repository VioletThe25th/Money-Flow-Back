package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelUser;
import com.example.Money.Flow.service.ModelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ModelUserService userService;

    @GetMapping
    public Iterable<ModelUser> getAllUsers() {
        return userService.getAllUsers();
    }
}
