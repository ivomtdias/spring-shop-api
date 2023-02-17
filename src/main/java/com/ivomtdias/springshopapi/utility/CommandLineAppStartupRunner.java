package com.ivomtdias.springshopapi.utility;

import com.ivomtdias.springshopapi.model.dto.ProductDTO;
import com.ivomtdias.springshopapi.model.dto.UserDTO;
import com.ivomtdias.springshopapi.model.request.CreateProductRequest;
import com.ivomtdias.springshopapi.model.request.SignUpRequest;
import com.ivomtdias.springshopapi.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final Environment env;
    private final UserService userService;
    private final ProductService productService;
    private final StockService stockService;
    private final CartService cartService;
    private final AuthService authService;

    public CommandLineAppStartupRunner(Environment env, UserService userService, ProductService productService, StockService stockService, CartService cartService, AuthService authService) {
        this.env = env;
        this.userService = userService;
        this.productService = productService;
        this.stockService = stockService;
        this.cartService = cartService;
        this.authService = authService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(env.getActiveProfiles()[0].equals("testdata")){
            generateTestData();
        }
    }

    private void generateTestData() {
        log.warn("##### STARTING GENERATION OF TEST DATA #####");

        SignUpRequest signUpRequest = new SignUpRequest("fn1", "ln1", "email1", "pass1", "add1", "zip1", "country1");
        authService.signUp(signUpRequest);

        UserDTO user = userService.getUserByEmail(signUpRequest.getEmail());

        ProductDTO product;
        for(int i = 0; i < 3; i++) {
            product = productService.createProduct(
                    new CreateProductRequest(
                            "p" + i,
                            new Random().nextDouble(100.00) + 1.00
                    )
            );
            stockService.addStock(product.id(), 3);
        }

        log.warn("##### FINISHING GENERATION OF TEST DATA #####");

    }
}
