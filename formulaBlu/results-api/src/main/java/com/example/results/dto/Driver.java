package com.example.results.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Driver {
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate dateOfBirth;
    private Integer driverNumber;
    private Long teamId;
    private String bio;
    private String helmetColor;
}
