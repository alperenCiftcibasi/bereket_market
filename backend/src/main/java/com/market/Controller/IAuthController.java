package com.market.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import com.market.Dto.PasswordResetDTO;
import com.market.Dto.PasswordResetRequestDTO;

public interface IAuthController {

    @PostMapping("/forgot-password")
    ResponseEntity<String> forgotPassword(@RequestBody PasswordResetRequestDTO dto);

    @PostMapping("/reset-password")
    ResponseEntity<String> resetPassword(@RequestBody PasswordResetDTO dto);

}
