// com.market.Dto.CategoryResponseDTO.java
package com.market.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private List<ProductResponseDTO> products; 
}
