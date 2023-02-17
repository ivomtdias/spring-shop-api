package com.ivomtdias.springshopapi.service;

import com.ivomtdias.springshopapi.model.User;
import com.ivomtdias.springshopapi.model.dto.UserDTO;

import java.util.Set;
import java.util.UUID;

public interface UserService {

    UserDTO getUserById(UUID userId);
    UserDTO getUserByEmail(String email);
    User getLoggedInUser();
    Set<UserDTO> getAllUsers();
    void updateUserById(/*UpdateUserRequest*/);
    void deleteUserById(UUID userId);

}
