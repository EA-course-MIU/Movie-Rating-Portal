package com.example.service;

import com.example.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(name="PRODUCT-SERVICE", url = "http://localhost:58372")
@FeignClient("PRODUCT-SERVICE")
public interface ProductClient {
    @GetMapping("/products")
    Iterable<ProductDto> getAllProducts();
}
