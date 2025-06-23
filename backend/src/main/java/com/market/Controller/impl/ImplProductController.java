package com.market.Controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.market.Controller.IProductController;
import com.market.Dto.ProductRequestDTO;
import com.market.Dto.ProductResponseDTO;
import com.market.Services.IProductServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("rest/api/products")
public class ImplProductController implements IProductController {

    @Autowired
    private IProductServices productService;

    @Override
    @PostMapping(path = "/post")
    public ProductResponseDTO addProduct(@RequestBody @Valid ProductRequestDTO productdto) {
        return productService.save(productdto);
    }

    @Override
    @GetMapping(path = "/list")
    public List<ProductResponseDTO> getAllProducts() {
        return productService.findAll();
    }
}
