package com.market.Controller;

import com.market.Dto.ProductRequestDTO;
import com.market.Dto.ProductResponseDTO;

import java.util.List;

public interface IProductController {
    ProductResponseDTO addProduct(ProductRequestDTO productdto);
    List<ProductResponseDTO> getAllProducts();
}
