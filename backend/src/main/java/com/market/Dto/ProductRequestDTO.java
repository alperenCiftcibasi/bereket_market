package com.market.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {

    @NotBlank(message = "Ürün adı boş olamaz")
    private String name;

    private String description;

    @NotNull(message = "Fiyat zorunludur")
    @Min(value = 0, message = "Fiyat negatif olamaz")
    private Double price;

    @NotNull(message = "Stok miktarı zorunludur")
    @Min(value = 0, message = "Stok negatif olamaz")
    private Integer stock;

    @NotNull(message = "Kategori bilgisi zorunludur")
    private Long categoryId;
}
