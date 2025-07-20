package com.arg.shipment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShipmentResponseDTO {
    private Long id;
    private Long orderId;
    private String shipmentAddress;
    private String shipmentStatus;
    private LocalDateTime shipmentDate;
}
