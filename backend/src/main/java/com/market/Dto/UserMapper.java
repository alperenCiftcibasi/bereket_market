package com.market.Dto;

import com.market.Entities.User;

public class UserMapper {
    
    public static UserResponseDTO toDto(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail()
        );
    }

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
