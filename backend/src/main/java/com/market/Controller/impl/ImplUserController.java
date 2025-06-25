package com.market.Controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.market.Entities.Address;
import com.market.Controller.IUserController;
import com.market.Dto.AddressDTO;
import com.market.Dto.UserRequestDTO;
import com.market.Dto.UserResponseDTO;
import com.market.Dto.UserUpdateRequestDTO;
import com.market.Entities.User;
import com.market.Repository.UserRepository;
import com.market.Services.IUserServices;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("rest/api/users")
public class ImplUserController implements IUserController {

    @Autowired
    private IUserServices userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/post")
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO user) {
        return userService.save(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/list")
    public List<UserResponseDTO> getAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDTO dto) {
        return userService.updateUser(id, dto);
    }

    // --- Adres İşlemleri ---

    // Kullanıcının kendi adreslerini listele
    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(Authentication authentication) {
        User user = getUserFromAuth(authentication);
        List<AddressDTO> addresses = user.getAddresses().stream()
            .map(this::toAddressDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(addresses);
    }

    // Yeni adres ekle
    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> addUserAddress(Authentication authentication, @Valid @RequestBody AddressDTO dto) {
        User user = getUserFromAuth(authentication);
        Address newAddress = new Address();
        newAddress.setTitle(dto.getTitle());
        newAddress.setDetail(dto.getDetail());
        newAddress.setUser(user);

        user.getAddresses().add(newAddress);
        userRepository.save(user);

        return ResponseEntity.ok(toAddressDTO(newAddress));
    }

    // Adres sil
    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<Void> deleteUserAddress(Authentication authentication, @PathVariable Long addressId) {
        User user = getUserFromAuth(authentication);
        user.getAddresses().removeIf(addr -> addr.getId().equals(addressId));
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

    // Yardımcı metot
    private User getUserFromAuth(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    }

    private AddressDTO toAddressDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setTitle(address.getTitle());
        dto.setDetail(address.getDetail());
        return dto;
    }
}
