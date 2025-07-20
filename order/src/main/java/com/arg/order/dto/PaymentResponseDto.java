package com.arg.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PaymentResponseDto {

    private Long id;

    private Long orderId;

    private String paymentMethod;

    private Double amount;

    private String status;

    private LocalDateTime paymentDate;
}


