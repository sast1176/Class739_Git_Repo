package com.example.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.dto.UserDto;
import com.example.ems.model.User;
import com.example.ems.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired // It provides depenedency injection.
    private UserService userService;

    @PostMapping("/save")
    public User addUser(@RequestBody UserDto UserDto) {
        return userService.saveUser(UserDto);
    }

    @GetMapping("/{userId}")
    public User getUserbyId(@PathVariable String userId) {
        return userService.getUserbyId(userId);
    }

    @PutMapping("/{userId}")
    public User updateUserbyId(@PathVariable String userId, @RequestBody UserDto userDto) {
        return userService.updateUserbyId(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserbyId(@PathVariable String userId) {
        userService.deleteUserbyId(userId);
    }

    @GetMapping("/all")
    public List<User> getUserList() {
        return userService.getUserList();
    }
}
