package com.example.journey.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.util.List;

@Data
public class DaySchedule {
    private LocalDate date;
    @Transient
    private List<String> tasks;
}