package com.arg.user.user.controllers;


import com.arg.user.user.dto.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user/user-order")
public class OrderController {

    String product_service_base_url = "http://127.0.0.1:9001/product";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping()
    public Object createOrder(@RequestBody Cart cart) {

        Object result = restTemplate.getForObject(product_service_base_url+"/"+1, Object.class);
        System.out.println(result);

        Mono<Object> productMono = webClientBuilder.build()
                .get()
                .uri(product_service_base_url+"/"+1)
                .retrieve()
                .bodyToMono(Object.class);

        return  productMono;
    }

}
