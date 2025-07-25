package com.arg.order.kafka;

import com.arg.order.dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final String topic = "ms-shipment-topic-1";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = topic, groupId = "order-group-1")
    public void consume(String message) {
        try {
            OrderDto order = objectMapper.readValue(message, OrderDto.class);
            System.out.printf("Received order: " + order);
        } catch (Exception e) {
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }

}
