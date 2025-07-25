package com.arg.order.services;// service/OrderService.java


import com.arg.order.dto.OrderDto;
import com.arg.order.entities.Order;
import com.arg.order.enums.OrderStatus;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDto order);

    Order getOrderById(Long id);

    List<Order> getAllOrders();

    Order updateOrder(Long id, Order order);

    void deleteOrder(Long id);

    Order updateStatus(Long orderId, String status);
}
