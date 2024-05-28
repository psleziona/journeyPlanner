package com.example.journey.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Table("trips")
@Data
public class Trip {
    @Id
    private Long id;
    private String name;
    private LocalDate start;
    private LocalDate finish;
    private List<String> images;
    private List<DaySchedule> schedules;
    private List<User> users;
    private List<String> comments;
}