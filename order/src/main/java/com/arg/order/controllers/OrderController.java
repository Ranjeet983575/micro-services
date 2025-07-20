package com.arg.order.controllers;

import com.arg.order.dto.OrderDto;
import com.arg.order.entities.Order;
import com.arg.order.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Mono<Order>> create(@RequestBody OrderDto order) {
        return ResponseEntity.ok(service.createOrder(order));
    }

    @GetMapping
    public List<Order> getAll() {
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order) {
        return ResponseEntity.ok(service.updateOrder(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
