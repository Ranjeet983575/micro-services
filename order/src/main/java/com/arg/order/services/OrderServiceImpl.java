package com.arg.order.services;

import com.arg.order.dto.OrderDto;
import com.arg.order.entities.Order;
import com.arg.order.exceptions.ResourceNotFoundException;
import com.arg.order.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repo;

    public OrderServiceImpl(OrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public Order createOrder(OrderDto order) {
        Order newOrder = new Order();
        newOrder.setCustomerName(order.getCustomerName());
        newOrder.setQuantity(order.getQuantity());
        newOrder.setPrice(order.getPrice());
        newOrder.setProductId(order.getProductId());
        return repo.save(newOrder);
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
}
