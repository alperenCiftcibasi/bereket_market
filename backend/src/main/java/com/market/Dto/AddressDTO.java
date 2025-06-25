package com.market.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDTO {
    private Long id;

    @NotBlank(message = "Adres başlığı boş olamaz")
    private String title;

    @NotBlank(message = "Adres detayı boş olamaz")
    private String detail;
    
    
}
