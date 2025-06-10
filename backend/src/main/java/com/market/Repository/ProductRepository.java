package com.market.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
