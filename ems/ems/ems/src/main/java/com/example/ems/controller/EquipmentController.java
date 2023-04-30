package com.example.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.dto.EquipmentDto;
import com.example.ems.dto.UserDto;
import com.example.ems.model.Equipment;
import com.example.ems.model.User;
import com.example.ems.service.EquipmentService;

@RestController
@RequestMapping("/equipment")
@CrossOrigin("*")
public class EquipmentController {
    @Autowired // It provides depenedency injection.
    private EquipmentService equipmentService;

    @PostMapping("/save")
    public Equipment addEquipmentForUser(@RequestBody EquipmentDto EquipmentDto) throws Exception {
        return equipmentService.addEquipmentForUser(EquipmentDto);
    }

    @GetMapping("/{equipmentId}")
    public Equipment getEquipmentbyId(@PathVariable String equipmentId) {
        return equipmentService.getEquipmentbyId(equipmentId);
    }

    @GetMapping("/user/{userId}")
    public List<Equipment> getEquipmentbyUserId(@PathVariable String userId) {
        return equipmentService.getEquipmentListbyUserId(userId);
    }

    @PutMapping("/{equipmentId}")
    public Equipment updateequipmentIdbyId(@PathVariable String equipmentId, @RequestBody EquipmentDto equipmentDto)
            throws Exception {
        return equipmentService.updateEquipmentbyId(equipmentId, equipmentDto);
    }

    @DeleteMapping("/{equipmentId}")
    public void deleteEquipmentbyId(@PathVariable String equipmentId) {
        equipmentService.deleteEquipmentbyId(equipmentId);
    }

    @GetMapping("/all")
    public List<Equipment> getEquipmentList() {
        return equipmentService.getEquipmentList();
    }

}
