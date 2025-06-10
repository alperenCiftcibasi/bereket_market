package com.market.Controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.Controller.ICategoryController;
import com.market.Entities.Category;
import com.market.Services.ICategoryServices;

@RestController
@RequestMapping("/api/categories")
public class ImplCategoryController implements ICategoryController {

    @Autowired
    private ICategoryServices categoryService;

    @Override
    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @Override
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }
}