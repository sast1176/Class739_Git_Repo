package com.example.ems.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentDto {
    private String model;
    private String serialNumber;
    private String equipmentName;
    private LocalDate calibrationDate;
    private String deviceType;
    private String userId;
}
