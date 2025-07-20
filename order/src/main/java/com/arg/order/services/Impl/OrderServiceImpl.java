package com.arg.order.services.Impl;

import com.arg.order.dto.OrderDto;
import com.arg.order.dto.PaymentRequestDto;
import com.arg.order.dto.PaymentResponseDto;
import com.arg.order.entities.Order;
import com.arg.order.enums.OrderStatus;
import com.arg.order.exceptions.ResourceNotFoundException;
import com.arg.order.repositories.OrderRepository;
import com.arg.order.services.OrderService;
import com.arg.order.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repo;
    private final PaymentService paymentService;

    @Override
    public Mono<Order> createOrder(OrderDto order) {
        Order newOrder = new Order();
        newOrder.setCustomerName(order.getCustomerName());
        newOrder.setQuantity(order.getQuantity());
        newOrder.setPrice(order.getPrice());
        newOrder.setProductId(order.getProductId());
        newOrder.setStatus(OrderStatus.CREATED);

        Order save = repo.save(newOrder);
        PaymentRequestDto request = new PaymentRequestDto();
        request.setAmount(order.getPrice() * order.getQuantity());
        request.setPaymentMethod("CARD");
        request.setOrderId(save.getId());

        return paymentService.makePayment(request)
                .map(response -> save)
                .onErrorResume(e -> {
                    System.out.println("Error Occurred while Payment");
                    save.setStatus(OrderStatus.FAILED);
                    save.setDescription("Error while payment : " + e.getMessage());
                    repo.save(save);
                    return Mono.just(save);
                });
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
    public Order updateStatus(Long orderId, OrderStatus status) {
        return repo.findById(orderId)
                .map(order -> {
                    order.setStatus(status);
                    return repo.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }
}
