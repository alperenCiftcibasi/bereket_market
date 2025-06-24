package com.market.Controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.Controller.IUserController;
import com.market.Dto.UserRequestDTO;
import com.market.Dto.UserResponseDTO;
import com.market.Dto.UserUpdateRequestDTO;
import com.market.Entities.User;
import com.market.Services.IUserServices;

import jakarta.validation.Valid;

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
    public UserResponseDTO updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateRequestDTO dto) {
        return userService.updateUser(id, dto);
    }

}