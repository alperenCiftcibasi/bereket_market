package com.market.Dto;

import com.market.Entities.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public static CategoryResponseDTO toDto(Category category) {
        List<ProductResponseDTO> products = category.getProducts().stream()
            .map(ProductMapper::toResponseDto)
            .collect(Collectors.toList());

        return new CategoryResponseDTO(
            category.getId(),
            category.getName(),
            products
        );
    }
}
