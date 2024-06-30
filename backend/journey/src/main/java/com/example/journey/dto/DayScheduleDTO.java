package com.example.journey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class DayScheduleDTO {
    private Long id;
    private String tripName;
    private LocalDate date;
    private List<String> tasks;
}
