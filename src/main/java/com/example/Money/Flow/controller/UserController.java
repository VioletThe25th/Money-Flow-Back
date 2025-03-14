package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelUser;
import com.example.Money.Flow.service.ModelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ModelUserService userService;

    // ✅ GET ALL USERS
    @GetMapping
    public ResponseEntity<List<ModelUser>> getAllUsers() {
        List<ModelUser> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelUser> getUserById(@PathVariable BigInteger id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ModelUser> createUser(@RequestBody ModelUser user) {
        try {
            ModelUser newUser = userService.createUser(user);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelUser> updateUser(@PathVariable BigInteger id, @RequestBody ModelUser user) {
        try {
            ModelUser updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable BigInteger id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
