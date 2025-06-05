package com.arg.user.user.controllers;


import com.arg.user.user.dto.ApiResponse;
import com.arg.user.user.dto.Cart;
import com.arg.user.user.dto.ProductDto;
import com.arg.user.user.feignClients.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user/user-order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${product.service.baseUrl}")
    private String productBaseUrl;

    @Autowired
    private ProductClient productClient;

    @PostMapping()
    public Object createOrder(@RequestBody Cart cart) {

//        ResponseEntity<ApiResponse<ProductDto>> response = restTemplate.exchange(
//                productBaseUrl + "product/" + 1,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<ApiResponse<ProductDto>>() {
//                }
//        );

//        Mono<ApiResponse<ProductDto>> productMono = webClientBuilder.build()
//                .get()
//                .uri(productBaseUrl + "product/" + 1)
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<ApiResponse<ProductDto>>() {
//                });
//
//        productMono.subscribe(apiResponse -> {
//            ProductDto product = apiResponse.getData();
//            System.out.println(product.getName());
//        });

        ApiResponse<ProductDto> response = productClient.getProductById1(1L);
        return ResponseEntity.ok(response);
    }


    @GetMapping()
    public Object getAllCategory() {
        ApiResponse<Object> response = null;
        try {
            response = productClient.getAllCategory();
        } catch (Exception e) {

            System.out.println("ERROR" + e);
        }
        return ResponseEntity.ok(response);
    }
}
