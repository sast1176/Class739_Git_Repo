package com.example.ems.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ems.model.Equipment;

public interface EquipmentRepository extends MongoRepository<Equipment, String> {

    List<Equipment> findByUserId(String userId);

}
