package com.arg.shipment.controller;

import com.arg.shipment.dto.OrderDto;
import com.arg.shipment.dto.ShipmentResponseDTO;
import com.arg.shipment.dto.ShipmentUpdateDTO;
import com.arg.shipment.entities.Shipment;
import com.arg.shipment.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<ShipmentResponseDTO> createShipment(@Valid @RequestBody OrderDto dto) {
        Shipment shipment = shipmentService.createShipment(dto);
        return ResponseEntity.ok(toDto(shipment));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ShipmentResponseDTO> getShipmentByOrderId(@PathVariable Long orderId) {
        Shipment shipment = shipmentService.getShipmentByOrderId(orderId);
        return ResponseEntity.ok(toDto(shipment));
    }

    @PutMapping("/{shipmentId}/status")
    public ResponseEntity<ShipmentResponseDTO> updateShipmentStatus(
            @PathVariable Long shipmentId,
            @Valid @RequestBody ShipmentUpdateDTO updateDTO
    ) {
        Shipment updated = shipmentService.updateShipmentStatus(shipmentId, updateDTO.getShipmentStatus());
        return ResponseEntity.ok(toDto(updated));
    }

    private ShipmentResponseDTO toDto(Shipment shipment) {
        return ShipmentResponseDTO.builder()
                .id(shipment.getId())
                .orderId(shipment.getOrderId())
                .shipmentAddress(shipment.getShipmentAddress())
                .shipmentStatus(shipment.getShipmentStatus().toString())
                .shipmentDate(shipment.getShipmentDate())
                .build();
    }
}
