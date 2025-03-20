package com.arg.user.service.entities;

import com.arg.user.service.dto.Rating;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "micro_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    private String name;

    private String email;

    @Transient
    private List<Rating> ratings=new ArrayList<>();
}

