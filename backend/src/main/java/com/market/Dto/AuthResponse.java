package com.market.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("role")
    private String role;

    private String message; // opsiyonel: hata durumlarında kullanılır

    public AuthResponse() {
    }

    // Başarılı login için
    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    // Hatalı login için
    public AuthResponse(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
