package com.example.ems.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ems.constants.Constants;
import com.example.ems.dto.EquipmentDto;
import com.example.ems.exceptions.DataUpdationFailedException;
import com.example.ems.exceptions.InvalidInputException;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.model.Equipment;
import com.example.ems.model.User;
import com.example.ems.repository.EquipmentRepository;
import com.example.ems.repository.UserRepository;
import com.example.ems.service.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Equipment addEquipmentForUser(EquipmentDto equipmentDto) throws Exception {

        if (equipmentDto == null) {
            throw new InvalidInputException(Constants.REQUEST_CANT_BE_NULL);
        }
        if (equipmentDto.getUserId() == null || equipmentDto.getUserId().isEmpty()) {
            throw new InvalidInputException(Constants.USER_ID_CANT_BE_NULL_OR_EMPTY);
        }
        Optional<User> user = userRepository.findById(equipmentDto.getUserId());
        if (!user.isPresent()) {
            throw new InvalidInputException(Constants.USER_NOT_FOUND);
        }

        if (equipmentDto.getModel() == null || equipmentDto.getModel().isEmpty()) {
            throw new InvalidInputException(Constants.EQUIPMENT_NAME_CANT_BE_NULL_OR_EMPTY);
        }
        if (equipmentDto.getSerialNumber() == null || equipmentDto.getSerialNumber().isEmpty()) {
            throw new InvalidInputException(Constants.EQUIPMENT_SERIALNUMBER_CANT_BE_NULL_OR_EMPTY);
        }
        if (equipmentDto.getDeviceType() == null || equipmentDto.getDeviceType().isEmpty()) {

            throw new InvalidInputException(Constants.EQUIPMENT_TYPE_CANT_BE_NULL_OR_EMPTY);
        }

        Equipment equipment = new Equipment();
        equipment.setModel(equipmentDto.getModel());
        equipment.setSerialNumber(equipmentDto.getSerialNumber());
        equipment.setDeviceType(equipmentDto.getDeviceType());
        equipment.setCalibrationDate(equipmentDto.getCalibrationDate());
        equipment.setUserId(equipmentDto.getUserId());
        equipment.setEquipmentName(equipmentDto.getEquipmentName());

        Equipment savedEquipment = equipmentRepository.save(equipment);

        if (savedEquipment == null) {
            throw new DataUpdationFailedException(Constants.EQUIPMENT_NOT_SAVED);
        }
        return savedEquipment;

    }

    @Override
    public Equipment getEquipmentbyId(String equipmentId) {
        if (equipmentId == null || equipmentId.isEmpty()) {
            throw new InvalidInputException(Constants.EQUIPMENT_ID_CANT_BE_NULL_OR_EMPTY);
        }
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);
        if (!equipment.isPresent()) {
            throw new ResourceNotFoundException(Constants.EQUIPMENT_NOT_FOUND);
        }
        return equipment.get();
    }

    @Override
    public void deleteEquipmentbyId(String equipmentId) {
        if (equipmentId == null || equipmentId.isEmpty()) {
            throw new InvalidInputException(Constants.EQUIPMENT_ID_CANT_BE_NULL_OR_EMPTY);
        }
        equipmentRepository.deleteById(equipmentId);
    }

    @Override
    public List<Equipment> getEquipmentList() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment updateEquipmentbyId(String equipmentId, EquipmentDto equipmentDto) throws Exception {

        if (equipmentId == null || equipmentId.isEmpty()) {
            throw new InvalidInputException(Constants.EQUIPMENT_ID_CANT_BE_NULL_OR_EMPTY);
        }
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(equipmentId);
        if (!optionalEquipment.isPresent()) {
            throw new ResourceNotFoundException(Constants.OPTIONAL_EQUIPMENT_NOT_FOUND);
        }

        if (equipmentDto.getDeviceType() == null || equipmentDto.getDeviceType().isEmpty()) {
            throw new InvalidInputException(Constants.EQUIPMENT_TYPE_CANT_BE_NULL_OR_EMPTY);
        }

        Equipment equipment = optionalEquipment.get();
        equipment.setEquipmentName(equipmentDto.getEquipmentName());
        equipment.setModel(equipmentDto.getModel());
        equipment.setSerialNumber(equipmentDto.getSerialNumber());
        equipment.setCalibrationDate(equipmentDto.getCalibrationDate());
        equipment.setDeviceType(equipmentDto.getDeviceType());
        Equipment updatedEquipment = equipmentRepository.save(equipment);
        return updatedEquipment;
    }

    @Override
    public List<Equipment> getEquipmentListbyUserId(String userId) {

        if (userId == null || userId.isEmpty()) {
            throw new InvalidInputException(Constants.USER_ID_CANT_BE_NULL_OR_EMPTY);
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new InvalidInputException(Constants.USER_NOT_FOUND);
        }
        return equipmentRepository.findByUserId(userId);

    }

}
