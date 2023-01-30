package com.ivomtdias.springshopapi.service.impl;

import com.ivomtdias.springshopapi.exception.ProductAlreadyExistsException;
import com.ivomtdias.springshopapi.exception.ProductNotFoundException;
import com.ivomtdias.springshopapi.exception.StockNotFoundException;
import com.ivomtdias.springshopapi.model.Product;
import com.ivomtdias.springshopapi.model.Stock;
import com.ivomtdias.springshopapi.model.dto.ProductDTO;
import com.ivomtdias.springshopapi.model.mapper.ProductDTOMapper;
import com.ivomtdias.springshopapi.model.request.CreateProductRequest;
import com.ivomtdias.springshopapi.model.request.UpdateProductRequest;
import com.ivomtdias.springshopapi.repository.ProductRepository;
import com.ivomtdias.springshopapi.repository.StockRepository;
import com.ivomtdias.springshopapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final StockRepository stockRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
        this.stockRepository = stockRepository;
    }

    @Override
    public ProductDTO createProduct(final CreateProductRequest createProductRequest) {
        getProductByName(createProductRequest.getName())
                .ifPresent(
                        product -> {
                            throw new ProductAlreadyExistsException(createProductRequest.getName());
                        });

        Product product = Product.builder()
                .name(createProductRequest.getName())
                .price(createProductRequest.getPrice())
                .build();

        stockRepository.save(new Stock(product, 0));

        return productDTOMapper.apply(
                productRepository.save(
                        product
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
        List<Product> products = productRepository.findProductsByNameContainingIgnoreCase(productName);
        if(products.isEmpty())
            throw new ProductNotFoundException(productName);

        return products
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toSet());
    }

    @Override
    public void updateProduct(final UUID productId, final UpdateProductRequest updateProductRequest) {
        if(productRepository.findById(productId).isEmpty())
            throw new ProductNotFoundException(productId);

        productRepository.updateProductById(updateProductRequest.getName(), updateProductRequest.getPrice(), productId);
    }

    @Override
    public void deleteProductById(final UUID productId) {
        if(productRepository.findById(productId).isEmpty())
            throw new ProductNotFoundException(productId);

        Optional<Stock> stock = stockRepository.findStockById(productId);
        if(stock.isEmpty())
            throw new StockNotFoundException(productId);

        stockRepository.deleteById(stock.get().getId());

        productRepository.deleteById(productId);
    }

    private Optional<ProductDTO> getProductByName(String productName){
        return productRepository.findProductByNameIgnoreCase(productName)
                .stream()
                .map(productDTOMapper)
                .findAny();
    }
}
