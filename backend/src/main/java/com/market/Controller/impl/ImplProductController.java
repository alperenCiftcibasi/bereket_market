package com.market.Controller.impl;

import java.util.List;

import javax.print.PrintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.Controller.IProductController;
import com.market.Entities.Product;
import com.market.Services.IProductServices;

@RestController
@RequestMapping("rest/api/products")
public class ImplProductController implements IProductController {

    @Autowired
    private IProductServices productService;

    @Override
    @PostMapping(path = "/post")
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @Override
    @GetMapping(path = "/list")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }
}