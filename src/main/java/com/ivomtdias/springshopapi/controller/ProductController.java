package com.ivomtdias.springshopapi.controller;

import com.ivomtdias.springshopapi.model.dto.ProductDTO;
import com.ivomtdias.springshopapi.model.request.CreateProductRequest;
import com.ivomtdias.springshopapi.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductRequest createProductRequest){
        return ResponseEntity.ok(productService.createProduct(createProductRequest));
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @GetMapping("{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
