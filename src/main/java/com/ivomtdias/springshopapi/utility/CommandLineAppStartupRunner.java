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

    private final Random random;

    public CommandLineAppStartupRunner(Environment env, UserService userService, ProductService productService, StockService stockService, CartService cartService, AuthService authService) {
        this.env = env;
        this.userService = userService;
        this.productService = productService;
        this.stockService = stockService;
        this.cartService = cartService;
        this.authService = authService;
        random = new Random();
    }

    @Override
    public void run(String... args) throws Exception {
        if(env.getActiveProfiles()[0].equals("testdata")){
            generateTestData();
        }
    }

    private void generateTestData() {

        SignUpRequest signUpRequest = new SignUpRequest("fn1", "ln1", "ivomtdias@gmail.com", "pass1", "add1", "zip1", "country1");
        authService.signUp(signUpRequest);

        SignUpRequest signUpRequestAdmin = new SignUpRequest("admin", "admin", "admin@gmail.com", "admin", "adminAddress", "adminZip", "adminCountry");
        authService.signUp(signUpRequestAdmin);

        UserDTO user = userService.getUserByEmail(signUpRequest.getEmail());

        ProductDTO product;
        for(int i = 0; i < 3; i++) {
            product = productService.createProduct(
                    new CreateProductRequest(
                            "p" + i,
                            random.nextDouble(100.00) + 1.00
                    )
            );
            stockService.addStock(product.id(), 3);
        }

        log.warn("""
                \n
                \t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                \t\t\t\t# ====> GENERATION OF TEST DATA COMPLETED <==== #
                \t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                # ====> DON'T FORGET TO UPDATE THE USERS TABLE TO ENABLE REQUESTS AS AN ADMIN <==== #
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """);

    }
}
