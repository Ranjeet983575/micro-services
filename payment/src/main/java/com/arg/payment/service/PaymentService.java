package com.arg.payment.service;

import com.arg.payment.dtos.PaymentRequestDTO;
import com.arg.payment.dtos.PaymentResponseDTO;
import com.arg.payment.entities.Payment;
import com.arg.payment.exception.ResourceNotFoundException;
import com.arg.payment.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment makePayment(PaymentRequestDTO dto) {
        Payment payment = Payment.builder()
                .orderId(dto.getOrderId())
                .paymentMethod(dto.getPaymentMethod())
                .amount(dto.getAmount())
                .status(com.arg.payment.enums.PaymentStatus.SUCCESS)
                .paymentDate(LocalDateTime.now())
                .build();
        return paymentRepository.save(payment);
    }


    public Payment getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment == null) {
            throw new ResourceNotFoundException("Payment not found for Order ID: " + orderId);
        }
        return payment;
    }

}
