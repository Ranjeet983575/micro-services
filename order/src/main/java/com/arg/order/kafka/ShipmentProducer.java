package com.arg.order.kafka;

import com.arg.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ShipmentProducer {

    private final KafkaTemplate<String, OrderDto> orderkafkaTemplate;

    private final String topic = "ms-shipment-topic-1";

    public void sendOrder(OrderDto order) {
        orderkafkaTemplate.send(topic, order.getProductId().toString(), order);
        System.out.println("Order sent: " + order);
    }
}
