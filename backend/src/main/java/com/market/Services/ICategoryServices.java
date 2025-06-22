package com.market.Services;

import java.util.List;

import com.market.Dto.CategoryRequestDTO;
import com.market.Dto.CategoryResponseDTO;

public interface ICategoryServices {
	CategoryResponseDTO save(CategoryRequestDTO category);
    List<CategoryResponseDTO> findAll();
}
