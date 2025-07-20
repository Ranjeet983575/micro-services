package com.arg.order.services;

import com.arg.order.dto.PaymentRequestDto;
import com.arg.order.dto.PaymentResponseDto;
import reactor.core.publisher.Mono;

public interface PaymentService {

    Mono<PaymentResponseDto> makePayment(PaymentRequestDto paymentRequestDto);

}
