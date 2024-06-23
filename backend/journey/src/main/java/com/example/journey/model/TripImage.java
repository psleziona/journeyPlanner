package com.example.journey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("trip_images")
@AllArgsConstructor
public class TripImage {
    private Long idTrip;
    private Long idOwner;
    private String filename;
}
