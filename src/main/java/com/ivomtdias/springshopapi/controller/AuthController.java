package com.ivomtdias.springshopapi.controller;

import com.ivomtdias.springshopapi.model.request.SignInRequest;
import com.ivomtdias.springshopapi.model.request.SignUpRequest;
import com.ivomtdias.springshopapi.model.response.SignUpResponse;
import com.ivomtdias.springshopapi.service.impl.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> register(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticate(@RequestBody SignInRequest request){
        return ResponseEntity.ok(authService.signIn(request).getToken());
    }
}
