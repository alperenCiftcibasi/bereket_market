package com.market.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.Entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
