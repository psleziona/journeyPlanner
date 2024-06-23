package com.example.journey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("trip_users")
@Data
@AllArgsConstructor
public class UserTrip {
    private Long idUser;
    private Long idTrip;
}
