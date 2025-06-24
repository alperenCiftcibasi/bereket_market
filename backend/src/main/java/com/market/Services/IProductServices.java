package com.market.Services;

import com.market.Dto.ProductRequestDTO;
import com.market.Dto.ProductResponseDTO;

import java.util.List;

public interface IProductServices {
    ProductResponseDTO save(ProductRequestDTO dto);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto);
    void deleteProduct(Long id);
    List<ProductResponseDTO> findAll();
}
