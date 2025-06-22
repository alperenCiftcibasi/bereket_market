package com.market.Controller;

import java.util.List;

import com.market.Dto.ProductRequestDTO;
import com.market.Dto.ProductResponseDTO;
import com.market.Entities.Product;

public interface IProductController {
	 ProductResponseDTO addProduct(ProductRequestDTO productdto);
	    List<ProductResponseDTO> getAllProducts();
}
