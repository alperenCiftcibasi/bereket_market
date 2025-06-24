package com.market.Services.impl;

import com.market.Dto.ProductMapper;
import com.market.Dto.ProductRequestDTO;
import com.market.Dto.ProductResponseDTO;
import com.market.Entities.Category;
import com.market.Entities.Product;
import com.market.Repository.CategoryRepository;
import com.market.Repository.ProductRepository;
import com.market.Services.IProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Product saved = productRepository.save(product);
        return ProductMapper.toResponseDto(saved);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setStock(dto.getStock());
        existing.setCategory(category);

        Product updated = productRepository.save(existing);
        return ProductMapper.toResponseDto(updated);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        productRepository.delete(product);
    }

    @Override
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
