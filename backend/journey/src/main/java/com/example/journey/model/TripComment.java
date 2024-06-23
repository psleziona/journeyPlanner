package com.example.journey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("trip_comments")
@AllArgsConstructor
public class TripComment {
    private Long idTrip;
    private Long idUser;
    private String comment;
}
