package com.arg.user.user.feignClients;

import com.arg.user.user.dto.ApiResponse;
import com.arg.user.user.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "${product.service.baseUrl}")
public interface ProductClient {

    @GetMapping("/product/{id}")
    ApiResponse<ProductDto> getProductById1(@PathVariable("id") Long id);

    @GetMapping("/category")
    ApiResponse<Object> getAllCategory();
}

