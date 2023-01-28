package com.ivomtdias.springshopapi.controller;

import com.ivomtdias.springshopapi.model.dto.ProductDTO;
import com.ivomtdias.springshopapi.model.request.CreateProductRequest;
import com.ivomtdias.springshopapi.model.request.UpdateProductRequest;
import com.ivomtdias.springshopapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
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

    @Secured({"ADMIN", "CUSTOMER"})
    @GetMapping()
    public ResponseEntity<Set<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @GetMapping("/name/{productName}")
    public ResponseEntity<Set<ProductDTO>> getProductsByName(@PathVariable String productName){
        return ResponseEntity.ok(productService.getProductsByName(productName));
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @PutMapping("{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable UUID productId, @RequestBody UpdateProductRequest updateProductRequest){
        productService.updateProduct(productId, updateProductRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID productId){
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
