package com.market.Services;

public interface IAuthService {
    void sendPasswordResetToken(String email);
    void resetPassword(String token, String newPassword);
}
