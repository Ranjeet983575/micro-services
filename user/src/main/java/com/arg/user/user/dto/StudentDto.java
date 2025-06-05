package com.arg.user.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private String name;
    private List<String> courses;
}
