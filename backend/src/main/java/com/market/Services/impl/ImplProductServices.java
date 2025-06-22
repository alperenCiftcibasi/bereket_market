package com.market.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.Dto.ProductMapper;
import com.market.Dto.ProductRequestDTO;
import com.market.Dto.ProductResponseDTO;
import com.market.Entities.Category;
import com.market.Entities.Product;
import com.market.Repository.CategoryRepository;
import com.market.Repository.ProductRepository;
import com.market.Services.IProductServices;

@Service
public class ImplProductServices implements IProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductResponseDTO save(ProductRequestDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        Product product = ProductMapper.toEntity(dto, category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toResponseDto(savedProduct);
    }

    @Override
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
