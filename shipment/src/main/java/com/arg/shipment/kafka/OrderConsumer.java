package com.arg.shipment.kafka;

import com.arg.shipment.dto.OrderDto;
import com.arg.shipment.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final String topic = "ms-shipment-topic-1";
    private final ShipmentService shipmentService;

    @KafkaListener(topics = topic, groupId = "order-group", containerFactory = "orderKafkaListenerContainerFactory")
    public void consume(OrderDto order) {
        try {
            System.out.printf("Shipment Received:" + order);
            shipmentService.createShipment(order);

        } catch (Exception e) {
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }

}
