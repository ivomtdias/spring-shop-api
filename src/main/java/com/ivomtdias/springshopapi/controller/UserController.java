package com.ivomtdias.springshopapi.controller;

import com.ivomtdias.springshopapi.model.User;
import com.ivomtdias.springshopapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @GetMapping()
    @Deprecated
    public ResponseEntity<User> getLoggedInUser(){
        return ResponseEntity.ok(userService.getLoggedInUser());
    }
}
