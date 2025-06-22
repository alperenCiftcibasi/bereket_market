package com.market.Controller;

import java.util.List;

import com.market.Dto.UserRequestDTO;
import com.market.Dto.UserResponseDTO;
import com.market.Entities.User;

public interface IUserController {
	UserResponseDTO createUser(UserRequestDTO user);
    List<UserResponseDTO> getAllUsers();
}
