package com.market.Services;

import java.util.List;

import com.market.Dto.UserRequestDTO;
import com.market.Dto.UserResponseDTO;
import com.market.Dto.UserUpdateRequestDTO;
import com.market.Entities.User;

public interface IUserServices {
	UserResponseDTO save(UserRequestDTO user);
    List<UserResponseDTO> findAll();
    void deleteUser(Long id);
    UserResponseDTO updateUser(Long id, UserUpdateRequestDTO dto);
    User getCurrentUser();


}
