package com.market.Dto;

import com.market.Entities.Category;
import com.market.Entities.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(category);
        product.setImageUrl(dto.getImageUrl());  // Eğer DTO'da varsa
        return product;
    }

    public static ProductResponseDTO toResponseDto(Product product) {
        return new ProductResponseDTO(
            product.getId(),                    // id eklendi
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getImageUrl(),             // imageUrl eklendi
            product.getCategory() != null ? product.getCategory().getName() : null
        );
    }
}
