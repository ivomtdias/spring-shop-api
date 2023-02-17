package com.ivomtdias.springshopapi.service.impl;

import com.ivomtdias.springshopapi.exception.ProductNotFoundException;
import com.ivomtdias.springshopapi.exception.ProductNotInCartException;
import com.ivomtdias.springshopapi.exception.StockNotFoundException;
import com.ivomtdias.springshopapi.model.*;
import com.ivomtdias.springshopapi.model.dto.CartProductDTO;
import com.ivomtdias.springshopapi.model.mapper.CartProductDTOMapper;
import com.ivomtdias.springshopapi.repository.CartProductRepository;
import com.ivomtdias.springshopapi.repository.CartRepository;
import com.ivomtdias.springshopapi.repository.ProductRepository;
import com.ivomtdias.springshopapi.repository.StockRepository;
import com.ivomtdias.springshopapi.service.CartService;
import com.ivomtdias.springshopapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final UserService userService;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final CartProductDTOMapper cartProductDTOMapper;

    public CartServiceImpl(CartRepository cartRepository, CartProductRepository cartProductRepository, UserService userService, StockRepository stockRepository, ProductRepository productRepository, CartProductDTOMapper cartProductDTOMapper) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.userService = userService;
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
        this.cartProductDTOMapper = cartProductDTOMapper;
    }


    @Override
    public CartProductDTO addProduct(UUID productId) {
        User user = userService.getLoggedInUser();

        Optional<Stock> stock = stockRepository.findById(productId);
        if(stock.isEmpty() || stock.get().getStockStatus().equals(StockStatus.OUT_OF_STOCK))
            throw new StockNotFoundException(productId);

        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new ProductNotFoundException(productId);

        Optional<Cart> cart = cartRepository.findById(user.getCart().getId());

        CartProduct cartProduct = cartProductRepository.save(CartProduct.builder()
                .cart(cart.get())
                .product(product.get())
                .build()
        );

        return cartProductDTOMapper.apply(cartProduct);

    }

    @Override
    public void removeProduct(UUID productId) {
        User user = userService.getLoggedInUser();

        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new ProductNotFoundException(productId);

        Optional<Cart> cart = cartRepository.findById(user.getCart().getId());

        Optional<CartProduct> cartProduct =
                cartProductRepository.findByCartIdAndProductId(cart.get().getId(), productId).stream()
                        .findFirst()
                        .orElseThrow(
                            () -> new ProductNotInCartException(cart.get().getId(), productId)
                        );

        cartProductRepository.delete(cartProduct.get());
    }
}
