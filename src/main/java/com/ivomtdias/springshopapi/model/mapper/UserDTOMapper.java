package com.ivomtdias.springshopapi.model.mapper;

import com.ivomtdias.springshopapi.model.User;
import com.ivomtdias.springshopapi.model.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getAddress(),
                user.getZipCode(),
                user.getCountry()
        );
    }
}
