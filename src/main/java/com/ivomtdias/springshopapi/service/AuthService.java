package com.ivomtdias.springshopapi.service;

import com.ivomtdias.springshopapi.model.request.SignInRequest;
import com.ivomtdias.springshopapi.model.request.SignUpRequest;
import com.ivomtdias.springshopapi.model.response.SignInResponse;
import com.ivomtdias.springshopapi.model.response.SignUpResponse;

public interface AuthService {
    SignUpResponse signUp(SignUpRequest request);
    SignInResponse signIn(SignInRequest request);

}
