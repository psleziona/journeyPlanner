package com.example.journey.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.List;

@Table("users")
@Data
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private List<Trip> trips;
}