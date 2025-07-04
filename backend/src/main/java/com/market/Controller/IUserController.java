package com.market.Controller;

import java.util.List;
import com.market.Dto.UserRequestDTO;
import com.market.Dto.UserResponseDTO;
import com.market.Dto.UserUpdateRequestDTO;

public interface IUserController {
    UserResponseDTO createUser(UserRequestDTO user);
    List<UserResponseDTO> getAllUsers();
    void deleteUser(Long id);
    UserResponseDTO updateUser(Long id, UserUpdateRequestDTO dto);
}
