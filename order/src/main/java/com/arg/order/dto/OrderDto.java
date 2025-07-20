package com.arg.order.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private String customerName;
    private Long productId;
    private Integer quantity;
    private Double price;
    private LocalDateTime orderDate;
}
