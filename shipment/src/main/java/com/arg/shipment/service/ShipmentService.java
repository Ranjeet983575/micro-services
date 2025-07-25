package com.arg.shipment.service;

import com.arg.shipment.dto.OrderDto;
import com.arg.shipment.entities.Shipment;
import com.arg.shipment.enums.ShipmentStatus;
import com.arg.shipment.exception.ResourceNotFoundException;
import com.arg.shipment.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;


    public Shipment createShipment(OrderDto dto) {
        Shipment shipment = Shipment.builder()
                .orderId(dto.getId())
                .shipmentAddress(dto.getAddress())
                .shipmentStatus(ShipmentStatus.CREATED)
                .shipmentDate(LocalDateTime.now())
                .customerName(dto.getCustomerName())
                .orderId(dto.getId())
                .build();
        return shipmentRepository.save(shipment);
    }

    public Shipment getShipmentByOrderId(Long orderId) {
        return shipmentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found for Order ID: " + orderId));
    }

    public Shipment updateShipmentStatus(Long shipmentId, ShipmentStatus newStatus) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with ID: " + shipmentId));

        shipment.setShipmentStatus(newStatus);
        return shipmentRepository.save(shipment);
    }
}
