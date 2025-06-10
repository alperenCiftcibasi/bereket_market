package com.market.Services;

import java.util.List;

import com.market.Entities.Category;

public interface ICategoryServices {
	Category save(Category category);
    List<Category> findAll();
}
