package com.arg.shipment.dto;

import com.arg.shipment.enums.ShipmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShipmentUpdateDTO {

    @NotNull
    private ShipmentStatus shipmentStatus;
}
