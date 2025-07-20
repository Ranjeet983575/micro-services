package com.arg.order.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private Long productId;
    private Integer quantity;
    private Double price;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime orderDate;
}
