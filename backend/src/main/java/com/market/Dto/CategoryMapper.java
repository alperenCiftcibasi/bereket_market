// com.market.Dto.CategoryMapper.java
package com.market.Dto;

import com.market.Entities.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public static CategoryResponseDTO toDto(Category category) {
        return new CategoryResponseDTO(
            category.getId(),
            category.getName()
        );
    }
}
