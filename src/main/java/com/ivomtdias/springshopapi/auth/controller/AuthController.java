package com.ivomtdias.springshopapi.auth.controller;

import com.ivomtdias.springshopapi.auth.model.request.SignInRequest;
import com.ivomtdias.springshopapi.auth.model.request.SignUpRequest;
import com.ivomtdias.springshopapi.auth.model.response.SignInResponse;
import com.ivomtdias.springshopapi.auth.model.response.SignUpResponse;
import com.ivomtdias.springshopapi.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> register(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> authenticate(@RequestBody SignInRequest request){
        return ResponseEntity.ok(authService.signIn(request));
    }
}
