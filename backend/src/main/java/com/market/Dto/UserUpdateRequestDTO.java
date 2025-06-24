package com.market.Dto;

import lombok.Data;

@Data
public class UserUpdateRequestDTO {
    private String name;
    private String email;
    private String role;  // ADMIN veya USER
    
    private String phone;
    private String address;
    private String fullName;
    private String paymentMethod;
}
