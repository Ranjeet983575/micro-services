package com.arg.shipment.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderShipmentDTO {

    private Long orderId;
    private Long shipmentId;
    private String status;

}
