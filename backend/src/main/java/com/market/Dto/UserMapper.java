package com.market.Dto;

import com.market.Entities.User;

public class UserMapper {
    
    public static UserResponseDTO toDto(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.getPhone(),
            user.getAddress(),
            user.getFullName(),
            user.getPaymentMethod()
        );
    }

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        user.setRole(dto.getRole() != null ? dto.getRole().toUpperCase() : "USER");
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setFullName(dto.getFullName());
        user.setPaymentMethod(dto.getPaymentMethod());

        return user;
    }

    public static void updateEntity(User user, UserUpdateRequestDTO dto) {
        if(dto.getName() != null) user.setName(dto.getName());
        if(dto.getEmail() != null) user.setEmail(dto.getEmail());
        if(dto.getRole() != null) user.setRole(dto.getRole().toUpperCase());

        if(dto.getPhone() != null) user.setPhone(dto.getPhone());
        if(dto.getAddress() != null) user.setAddress(dto.getAddress());
        if(dto.getFullName() != null) user.setFullName(dto.getFullName());
        if(dto.getPaymentMethod() != null) user.setPaymentMethod(dto.getPaymentMethod());
    }
}
