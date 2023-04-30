package com.example.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ems.dto.UserDto;
import com.example.ems.model.User;

@Service
public interface UserService {

    public User saveUser(UserDto userDto);

    public User getUserbyId(String userId);

    public void deleteUserbyId(String userId);

    public User updateUserbyId(String userId, UserDto userDto);

    public List<User> getUserList();
}
