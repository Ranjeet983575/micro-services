package com.arg.payment.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponseDTO {

    private Long id;
    private Long orderId;
    private String paymentMethod;
    private Double amount;
    private com.arg.payment.enums.PaymentStatus status;
    private LocalDateTime paymentDate;
}
