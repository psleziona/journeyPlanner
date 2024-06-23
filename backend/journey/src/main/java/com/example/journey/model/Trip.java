package com.example.journey.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
    private Long idOwner;
    @Transient
    private List<String> images;
    @Transient
    private List<DaySchedule> schedules;
    @Transient
    private List<User> users;
    @Transient
    private List<String> comments;
}