package com.arg.shipment.kafka;


import com.arg.shipment.dto.OrderDto;
import com.arg.shipment.service.ShipmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final String topic = "ms-shipment-topic-1";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ShipmentService shipmentService;


    @KafkaListener(topics = topic, groupId = "order-group")
    public void consume(String message) {
        try {
            OrderDto order = objectMapper.readValue(message, OrderDto.class);
            System.out.printf("Shipment Received:" + order);
            shipmentService.createShipment(order);

        } catch (Exception e) {
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }

}
