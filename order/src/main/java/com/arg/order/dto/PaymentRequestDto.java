package com.arg.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    private Long orderId;

    private String paymentMethod;

    private Double amount;
}
