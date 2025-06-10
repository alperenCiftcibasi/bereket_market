package com.market.Controller;

import java.util.List;

import com.market.Entities.Product;

public interface IProductController {
	 Product addProduct(Product product);
	    List<Product> getAllProducts();
}
