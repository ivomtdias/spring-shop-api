package com.ivomtdias.springshopapi.service;

import com.ivomtdias.springshopapi.model.dto.ProductDTO;
import com.ivomtdias.springshopapi.model.request.CreateProductRequest;
import com.ivomtdias.springshopapi.model.request.UpdateProductRequest;

import java.util.Set;
import java.util.UUID;

public interface ProductService {
    ProductDTO createProduct(CreateProductRequest createProductRequest);
    Set<ProductDTO> findAll();
    ProductDTO getProductById(UUID productId);
    Set<ProductDTO> getProductsByName(String productName);
    void updateProduct(UUID productId, UpdateProductRequest updateProductRequest);
    void deleteProductById(UUID productId);
}
