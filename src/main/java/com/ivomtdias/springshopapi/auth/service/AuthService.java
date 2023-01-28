package com.ivomtdias.springshopapi.auth.service;

import com.ivomtdias.springshopapi.auth.exception.UserAlreadyExistsException;
import com.ivomtdias.springshopapi.auth.model.Role;
import com.ivomtdias.springshopapi.auth.model.User;
import com.ivomtdias.springshopapi.auth.model.request.SignInRequest;
import com.ivomtdias.springshopapi.auth.model.request.SignUpRequest;
import com.ivomtdias.springshopapi.auth.model.response.SignInResponse;
import com.ivomtdias.springshopapi.auth.model.response.SignUpResponse;
import com.ivomtdias.springshopapi.auth.repository.UserRepository;
import com.ivomtdias.springshopapi.auth.utility.JWTUtility;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtility jwtUtility, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
    }

    public SignUpResponse signUp(SignUpRequest request){
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        existingUser.ifPresentOrElse(
                user -> {
                    throw new UserAlreadyExistsException(request.getEmail());
                },
                () -> userRepository.save(
                        User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .address(request.getAddress())
                        .zipCode(request.getZipCode())
                        .country(request.getCountry())
                        .role(Role.USER)
                        .build()
                )
        );
        return SignUpResponse.builder()
                .email(request.getEmail())
                .build();
    }

    public SignInResponse signIn(SignInRequest request){
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtUtility.generateToken(user);
        return SignInResponse.builder()
                .token(jwtToken)
                .build();
    }
}
