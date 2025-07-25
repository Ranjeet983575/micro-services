package com.arg.shipment.kafka;

import com.arg.shipment.dto.OrderShipmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentProducer {

    private final KafkaTemplate<String, OrderShipmentDTO> orderShipmentStatusKafkaTemplate;

    private final String topic = "ms-shipment-status";

    public void sendShipment(OrderShipmentDTO shipmentDTO) {
        orderShipmentStatusKafkaTemplate.send(topic, shipmentDTO.getOrderId().toString(), shipmentDTO);
        System.out.println("Shipment sent: " + shipmentDTO);
    }
}
