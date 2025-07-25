package com.arg.shipment.entities;

import com.arg.shipment.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private String shipmentAddress;

    private String customerName;

    @Column(nullable = false)
    private String shipmentStatus;// e.g., "CREATED", "IN_TRANSIT", "DELIVERED"

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime shipmentDate;
}
