package com.arg.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private UUID ratingId;
    private UUID userID;
    private UUID hotelID;
    private int rating;
    private String feedback;
}
