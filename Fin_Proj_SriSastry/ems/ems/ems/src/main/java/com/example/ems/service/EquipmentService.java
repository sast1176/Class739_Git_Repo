package com.example.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ems.dto.EquipmentDto;
import com.example.ems.model.Equipment;

@Service
public interface EquipmentService {

    public Equipment addEquipmentForUser(EquipmentDto equipmentDto) throws Exception;

    public Equipment getEquipmentbyId(String equipmentId);

    public void deleteEquipmentbyId(String equipmentId);

    public List<Equipment> getEquipmentList();

    public Equipment updateEquipmentbyId(String equipmentId, EquipmentDto equipmentDto) throws Exception;

    public List<Equipment> getEquipmentListbyUserId(String userId);

}
