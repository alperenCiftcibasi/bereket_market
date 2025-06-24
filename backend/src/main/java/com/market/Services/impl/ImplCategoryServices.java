package com.market.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.Dto.CategoryMapper;
import com.market.Dto.CategoryRequestDTO;
import com.market.Dto.CategoryResponseDTO;
import com.market.Entities.Category;
import com.market.Repository.CategoryRepository;
import com.market.Services.ICategoryServices;

@Service
public class ImplCategoryServices implements ICategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO save(CategoryRequestDTO dto) {
        Category category = CategoryMapper.toEntity(dto);
        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDto(saved);
    }

    @Override
    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı: " + id));
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı: " + id));
        existing.setName(dto.getName());
        Category updated = categoryRepository.save(existing);
        return CategoryMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Kategori bulunamadı: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
