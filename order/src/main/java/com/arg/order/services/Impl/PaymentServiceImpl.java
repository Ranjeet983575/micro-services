package com.arg.order.services.Impl;

import com.arg.order.dto.PaymentRequestDto;
import com.arg.order.dto.PaymentResponseDto;
import com.arg.order.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private WebClient webClient;

    @Value("${services.payment}")
    private String paymentBaseUrl;

    @Override
    public Mono<PaymentResponseDto> makePayment(PaymentRequestDto paymentRequestDto) {
        return webClient.post()
                .uri(paymentBaseUrl)
                .bodyValue(paymentRequestDto)
                .retrieve()
                .bodyToMono(PaymentResponseDto.class);
    }
}
