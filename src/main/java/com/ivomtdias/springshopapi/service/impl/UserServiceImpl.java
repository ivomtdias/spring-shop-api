package com.ivomtdias.springshopapi.service.impl;

import com.ivomtdias.springshopapi.exception.UserNotFoundException;
import com.ivomtdias.springshopapi.model.User;
import com.ivomtdias.springshopapi.model.dto.UserDTO;
import com.ivomtdias.springshopapi.model.mapper.UserDTOMapper;
import com.ivomtdias.springshopapi.repository.UserRepository;
import com.ivomtdias.springshopapi.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserServiceImpl(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public UserDTO getUserById(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new UserNotFoundException(userId);

        return userDTOMapper.apply(user.get());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty())
            throw new UserNotFoundException(email);

        return userDTOMapper.apply(user.get());
    }

    @Override
    public User getLoggedInUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> user = userRepository.findByEmail(principal.getUsername());
        if(user.isEmpty())
            throw new UserNotFoundException(principal.getUsername());

        return user.get();
    }

    @Override
    public Set<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userDTOMapper).collect(Collectors.toSet());
    }

    @Override
    public void updateUserById() {

    }

    @Override
    public void deleteUserById(UUID userId) {

    }
}
