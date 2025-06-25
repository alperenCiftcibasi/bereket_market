package com.market.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import com.market.Dto.AddressDTO;
import com.market.Dto.UserRequestDTO;
import com.market.Dto.UserResponseDTO;
import com.market.Dto.UserUpdateRequestDTO;

public interface IUserController {
    UserResponseDTO createUser(UserRequestDTO user);
    List<UserResponseDTO> getAllUsers();
    void deleteUser(Long id);
    UserResponseDTO updateUser(Long id, UserUpdateRequestDTO dto);

    ResponseEntity<List<AddressDTO>> getUserAddresses(Authentication authentication);
    ResponseEntity<AddressDTO> addUserAddress(Authentication authentication, AddressDTO dto);
    ResponseEntity<Void> deleteUserAddress(Authentication authentication, Long addressId);
}
