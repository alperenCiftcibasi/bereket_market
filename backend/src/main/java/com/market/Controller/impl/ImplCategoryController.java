package com.market.Controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.market.Controller.ICategoryController;
import com.market.Dto.CategoryRequestDTO;
import com.market.Dto.CategoryResponseDTO;
import com.market.Services.ICategoryServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("rest/api/categories")
public class ImplCategoryController implements ICategoryController {

    @Autowired
    private ICategoryServices categoryService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/post")
    public CategoryResponseDTO addCategory(@RequestBody @Valid CategoryRequestDTO category) {
        return categoryService.save(category);
    }

    @Override
    @GetMapping("/list")
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.findAll();
    }

    @Override
    @GetMapping("/list/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public CategoryResponseDTO updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequestDTO category) {
        return categoryService.update(id, category);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
