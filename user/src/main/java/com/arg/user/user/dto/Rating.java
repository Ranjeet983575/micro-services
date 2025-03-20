package com.arg.user.user.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class Rating {
    private int id;
    private UUID userId;
    private UUID hotelId;
    private int rating;
    private String feedback;
}
