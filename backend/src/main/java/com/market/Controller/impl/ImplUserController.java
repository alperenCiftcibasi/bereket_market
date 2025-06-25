package com.market.Controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.market.Controller.IUserController;
import com.market.Dto.UserRequestDTO;
import com.market.Dto.UserResponseDTO;
import com.market.Dto.UserUpdateRequestDTO;
import com.market.Services.IUserServices;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("rest/api/users")
public class ImplUserController implements IUserController {

    @Autowired
    private IUserServices userService;

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
}
