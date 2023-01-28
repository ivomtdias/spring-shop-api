package com.ivomtdias.springshopapi.service.impl;

import com.ivomtdias.springshopapi.exception.ProductAlreadyExistsException;
import com.ivomtdias.springshopapi.exception.ProductNotFoundException;
import com.ivomtdias.springshopapi.model.Product;
import com.ivomtdias.springshopapi.model.dto.ProductDTO;
import com.ivomtdias.springshopapi.model.mapper.ProductDTOMapper;
import com.ivomtdias.springshopapi.model.request.CreateProductRequest;
import com.ivomtdias.springshopapi.repository.ProductRepository;
import com.ivomtdias.springshopapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    public ProductDTO createProduct(final CreateProductRequest createProductRequest) {
        getProductByName(createProductRequest.getName())
                .ifPresent(
                        product -> {
                            throw new ProductAlreadyExistsException(createProductRequest.getName());
                        });

        return productDTOMapper.apply(
                productRepository.save(
                        Product.builder()
                                .name(createProductRequest.getName())
                                .price(createProductRequest.getPrice())
                                .build()
                )
        );
    }

    @Override
    public Set<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(productDTOMapper).collect(Collectors.toSet());
    }

    @Override
    public ProductDTO getProductById(final UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> {
            throw new ProductNotFoundException(productId);
        });
        return productDTOMapper.apply(product);
    }

    @Override
    public Set<ProductDTO> getProductsByName(final String productName) {
        return productRepository.findProductsByNameContainingIgnoreCase(productName)
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toSet());
    }

    @Override
    public void updateProduct(final ProductDTO productDTO) {
        if(productRepository.findById(productDTO.id()).isEmpty())
            throw new ProductNotFoundException(productDTO.id());

        productRepository.updateProductById(productDTO.name(), productDTO.price(), productDTO.id());
    }

    @Override
    public void deleteProductById(final UUID productId) {
        if(productRepository.findById(productId).isEmpty())
            throw new ProductNotFoundException(productId);

        productRepository.deleteById(productId);
    }

    private Optional<ProductDTO> getProductByName(String productName){
        return productRepository.findProductByNameIgnoreCase(productName)
                .stream()
                .map(productDTOMapper)
                .findAny();
    }
}
