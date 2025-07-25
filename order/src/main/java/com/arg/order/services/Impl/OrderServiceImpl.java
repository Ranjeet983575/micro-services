package com.arg.order.services.Impl;

import com.arg.order.dto.OrderDto;
import com.arg.order.dto.PaymentRequestDto;
import com.arg.order.dto.PaymentResponseDto;
import com.arg.order.entities.Order;
import com.arg.order.enums.OrderStatus;
import com.arg.order.exceptions.ResourceNotFoundException;
import com.arg.order.kafka.ShipmentProducer;
import com.arg.order.repositories.OrderRepository;
import com.arg.order.services.OrderService;
import com.arg.order.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repo;
    private final PaymentService paymentService;
    private final ShipmentProducer shipmentProducer;

    @Override
    public Order createOrder(OrderDto orderDto) {
        // Build Order
        Order newOrder = new Order();
        newOrder.setCustomerName(orderDto.getCustomerName());
        newOrder.setQuantity(orderDto.getQuantity());
        newOrder.setPrice(orderDto.getPrice());
        newOrder.setProductId(orderDto.getProductId());
        newOrder.setAddress(orderDto.getAddress());
        newOrder.setStatus(OrderStatus.CREATED.toString());

        Order savedOrder = repo.save(newOrder);
        orderDto.setId(savedOrder.getId());

        System.out.println("DTO+++" + orderDto);

        // Prepare Payment Request
        PaymentRequestDto paymentRequest = new PaymentRequestDto();
        paymentRequest.setAmount(savedOrder.getPrice() * savedOrder.getQuantity());
        paymentRequest.setPaymentMethod("CARD");
        paymentRequest.setOrderId(savedOrder.getId());

        try {
            PaymentResponseDto paymentResponse = paymentService.makePayment(paymentRequest).block();
            savedOrder.setStatus(OrderStatus.PAYMENT.toString());
            System.out.println("Payment Response: " + paymentResponse);
            //Send Data to Shipment service
            shipmentProducer.sendOrder(orderDto);
            return repo.save(savedOrder);
        } catch (Exception e) {
            savedOrder.setStatus(OrderStatus.FAILED.toString());
            savedOrder.setDescription("Payment failed: " + e.getMessage());
            repo.save(savedOrder);
            System.err.println("Payment failed: " + e.getMessage());
            return savedOrder;
        }
    }

    @Override
    public Order getOrderById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        order.setCustomerName(orderDetails.getCustomerName());
        order.setProductId(orderDetails.getProductId());
        order.setQuantity(orderDetails.getQuantity());
        order.setPrice(orderDetails.getPrice());
        order.setOrderDate(orderDetails.getOrderDate());
        return repo.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Order updateStatus(Long orderId, String status) {
        return repo.findById(orderId)
                .map(order -> {
                    order.setStatus(status);
                    return repo.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }
}
