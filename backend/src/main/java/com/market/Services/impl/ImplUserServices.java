package com.market.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.Dto.UserMapper;
import com.market.Dto.UserRequestDTO;
import com.market.Dto.UserResponseDTO;
import com.market.Dto.UserUpdateRequestDTO;
import com.market.Entities.User;
import com.market.Repository.UserRepository;
import com.market.Services.IUserServices;

@Service
public class ImplUserServices implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO save(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateRequestDTO dto) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getRole() != null) user.setRole(dto.getRole().toUpperCase());
        
        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
        if (dto.getPaymentMethod() != null) user.setPaymentMethod(dto.getPaymentMethod());

        return UserMapper.toDto(userRepository.save(user));
    }

    
}
