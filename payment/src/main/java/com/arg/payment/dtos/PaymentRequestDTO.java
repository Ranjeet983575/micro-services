package com.arg.payment.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class PaymentRequestDTO {

    @NotNull
    private Long orderId;

    @NotBlank
    private String paymentMethod;

    @NotNull
    @Min(0)
    private Double amount;
}
