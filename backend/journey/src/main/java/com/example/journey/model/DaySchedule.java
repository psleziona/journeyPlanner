package com.example.journey.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Data
@Table("day_schedules")
public class DaySchedule {
    @Id
    private Long id;
    private LocalDate date;
    private Long idTrip;
    @Transient
    private List<String> tasks;
}