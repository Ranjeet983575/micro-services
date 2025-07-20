package com.arg.shipment.service;

import com.arg.shipment.dto.ShipmentRequestDTO;
import com.arg.shipment.entities.Shipment;
import com.arg.shipment.enums.ShipmentStatus;
import com.arg.shipment.exception.ResourceNotFoundException;
import com.arg.shipment.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Shipment createShipment(ShipmentRequestDTO dto) {
        Shipment shipment = Shipment.builder()
                .orderId(dto.getOrderId())
                .shipmentAddress(dto.getShipmentAddress())
                .shipmentStatus(ShipmentStatus.CREATED)
                .shipmentDate(LocalDateTime.now())
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
