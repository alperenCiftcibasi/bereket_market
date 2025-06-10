package com.market.Controller;

import java.util.List;

import com.market.Entities.Category;

public interface ICategoryController {
	Category addCategory(Category category);
    List<Category> getAllCategories();
}
