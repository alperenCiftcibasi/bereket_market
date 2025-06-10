package com.market.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.Entities.Category;
public interface CategoryRepository extends JpaRepository<Category, Long> {
}