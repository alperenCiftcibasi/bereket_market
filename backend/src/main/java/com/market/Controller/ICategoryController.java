package com.market.Controller;

import java.util.List;

import com.market.Dto.CategoryRequestDTO;
import com.market.Dto.CategoryResponseDTO;

public interface ICategoryController {
	CategoryResponseDTO addCategory(CategoryRequestDTO category);
    List<CategoryResponseDTO> getAllCategories();
}
