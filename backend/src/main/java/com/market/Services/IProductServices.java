package com.market.Services;

import java.util.List;

import com.market.Entities.Product;

public interface IProductServices {
	Product save(Product product);
    List<Product>  findAll();
}
