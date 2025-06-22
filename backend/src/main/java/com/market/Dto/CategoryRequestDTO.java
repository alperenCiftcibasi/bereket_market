// com.market.Dto.CategoryRequestDTO.java
package com.market.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {
	 @NotBlank(message = "Kategori adı boş olamaz")
	private String name;
}
