package com.arg.inventory.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
}
