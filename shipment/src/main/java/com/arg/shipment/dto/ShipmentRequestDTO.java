package com.arg.shipment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShipmentRequestDTO {

    @NotNull
    private Long orderId;

    @NotBlank
    private String shipmentAddress;
}
