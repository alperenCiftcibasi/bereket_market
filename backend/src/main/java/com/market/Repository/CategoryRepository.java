package com.market.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.Entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}