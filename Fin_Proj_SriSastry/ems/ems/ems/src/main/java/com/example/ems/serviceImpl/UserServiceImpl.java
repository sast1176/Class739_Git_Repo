package com.example.ems.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ems.constants.Constants;
import com.example.ems.dto.UserDto;
import com.example.ems.exceptions.DataUpdationFailedException;
import com.example.ems.exceptions.EmailAlreadyExistsException;
import com.example.ems.exceptions.InvalidInputException;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.model.Equipment;
import com.example.ems.model.User;
import com.example.ems.repository.EquipmentRepository;
import com.example.ems.repository.UserRepository;
import com.example.ems.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public User saveUser(UserDto userDto) {

        if (userDto == null) {
            throw new InvalidInputException(Constants.REQUEST_CANT_BE_NULL);
        }
        if (userDto.getFullName() == null || userDto.getFullName().isEmpty()) {
            throw new InvalidInputException(Constants.USER_NAME_CANT_BE_NULL_OR_EMPTY);
        }
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            throw new InvalidInputException(Constants.USER_EMAIL_CANT_BE_NULL_OR_EMPTY);
        }
        if (userDto.getPhoneNumber() == null || userDto.getPhoneNumber().isEmpty()) {
            throw new InvalidInputException(Constants.USER_PHONE_NUMBER_CANT_BE_NULL_OR_EMPTY);
        }
        if (userDto.getUserType() == null || userDto.getUserType().isEmpty()) {
            throw new InvalidInputException(Constants.USER_TYPE_CANT_BE_NULL_OR_EMPTY);
        }
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException(Constants.USER_EMAIL_ALREADY_TAKEN);
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUserType(userDto.getUserType());

        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            throw new DataUpdationFailedException(Constants.USER_NOT_SAVED);
        }
        return savedUser;

    }

    @Override
    public User getUserbyId(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new InvalidInputException(Constants.USER_ID_CANT_BE_NULL_OR_EMPTY);
        }
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
        }
        return user.get();
    }

    @Override
    public void deleteUserbyId(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new InvalidInputException(Constants.USER_ID_CANT_BE_NULL_OR_EMPTY);
        }

        List<Equipment> equipments = equipmentRepository.findByUserId(userId);
        if (!(equipments.isEmpty())) {
            for (Equipment equipment : equipments) {
                equipmentRepository.deleteById(equipment.getId());
            }
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUserbyId(String userId, UserDto userDto) {
        if (userId == null || userId.isEmpty()) {
            throw new InvalidInputException(Constants.USER_ID_CANT_BE_NULL_OR_EMPTY);
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
        }
        if (userDto.getFullName() == null || userDto.getFullName().isEmpty()) {
            throw new InvalidInputException(Constants.USER_NAME_CANT_BE_NULL_OR_EMPTY);
        }
        if (userDto.getPhoneNumber() == null || userDto.getPhoneNumber().isEmpty()) {
            throw new InvalidInputException(Constants.USER_PHONE_NUMBER_CANT_BE_NULL_OR_EMPTY);
        }
        User user = optionalUser.get();
        user.setFullName(userDto.getFullName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUserType(userDto.getUserType());
        User updatedUser = userRepository.save(user);
        if (updatedUser == null) {
            throw new DataUpdationFailedException(Constants.USER_NOT_UPDATED);
        }
        return updatedUser;
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

}
