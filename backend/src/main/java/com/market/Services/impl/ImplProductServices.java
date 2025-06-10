package com.market.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.Entities.Product;
import com.market.Repository.ProductRepository;
import com.market.Services.IProductServices;

@Service
public class ImplProductServices implements IProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}