package com.arg.user.user.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "ms_user_address")
@Data
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String city;
    private int pin;
    private String contact;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private UserEntity user;

}
