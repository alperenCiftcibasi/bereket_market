package com.market.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.Entities.PasswordResetToken;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}
