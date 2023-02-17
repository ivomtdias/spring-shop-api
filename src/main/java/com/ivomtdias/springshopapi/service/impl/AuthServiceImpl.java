package com.ivomtdias.springshopapi.service.impl;

import com.ivomtdias.springshopapi.exception.UserAlreadyExistsException;
import com.ivomtdias.springshopapi.exception.UserNotFoundException;
import com.ivomtdias.springshopapi.model.Cart;
import com.ivomtdias.springshopapi.model.Role;
import com.ivomtdias.springshopapi.model.User;
import com.ivomtdias.springshopapi.model.request.SignInRequest;
import com.ivomtdias.springshopapi.model.request.SignUpRequest;
import com.ivomtdias.springshopapi.model.response.SignInResponse;
import com.ivomtdias.springshopapi.model.response.SignUpResponse;
import com.ivomtdias.springshopapi.repository.UserRepository;
import com.ivomtdias.springshopapi.service.AuthService;
import com.ivomtdias.springshopapi.utility.JWTUtility;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtility jwtUtility, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
    }

    @Override
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
                        .role(Role.CUSTOMER)
                        .cart(Cart.builder().build())
                        .build()
                )
        );
        return SignUpResponse.builder()
                .email(request.getEmail())
                .build();
    }

    @Override
    public SignInResponse signIn(SignInRequest request){
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> {throw new UserNotFoundException(request.getEmail());}
        );
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
