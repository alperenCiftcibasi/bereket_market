package com.market.Services;

import java.util.List;


import com.market.Dto.ProductRequestDTO;
import com.market.Dto.ProductResponseDTO;


public interface IProductServices {
	ProductResponseDTO save(ProductRequestDTO dto);
    List<ProductResponseDTO>  findAll();
}
