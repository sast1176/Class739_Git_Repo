package com.example.ems.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Equipment")
public class Equipment {
    @Id
    private String id;
    private String equipmentName;
    private String model;
    private String serialNumber;
    private LocalDate calibrationDate;
    private String deviceType;
    private String userId;

}
