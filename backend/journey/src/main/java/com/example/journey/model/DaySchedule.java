package com.example.journey.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DaySchedule {
    private LocalDate date;
    private List<String> tasks;
}