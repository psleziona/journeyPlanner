package com.example.journey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserTripImagesDTO {
    public String tripName;
    public Long idTrip;
    public List<String> photos;
}
