package com.market.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.Entities.Category;
import com.market.Repository.CategoryRepository;
import com.market.Services.ICategoryServices;

@Service
public class ImplCategoryServices implements ICategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
