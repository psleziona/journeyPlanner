package com.example.journey.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("day_schedules_tasks")
public class DayScheduleTask {
    private Long idDaySchedule;
    private Long idUser;
    private String task;
}
