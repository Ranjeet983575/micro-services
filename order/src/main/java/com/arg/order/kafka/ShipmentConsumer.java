package com.arg.order.kafka;

import com.arg.order.dto.OrderDto;
import com.arg.order.dto.OrderShipmentDTO;
import com.arg.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentConsumer {

    private final String topic = "ms-shipment-topic-1";
    private final String topic_shipment_status = "ms-shipment-status";

    private final OrderService orderService;

    @KafkaListener(topics = topic, groupId = "order-group-1", containerFactory = "orderKafkaListenerContainerFactory")
    public void consume(OrderDto order) {
        try {
            System.out.printf("Received order: " + order);
        } catch (Exception e) {
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }

    @KafkaListener(topics = topic_shipment_status, groupId = "shipment-group-1", containerFactory = "orderShipmentKafkaListenerContainerFactory")
    public void consumeShipment(OrderShipmentDTO order) {
        try {
            System.out.printf("Received Shipment: " + order);
            orderService.updateStatus(order.getOrderId(), order.getStatus());

        } catch (Exception e) {
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }

}
