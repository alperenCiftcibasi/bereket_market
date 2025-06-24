package com.market.Controller;

import com.market.Dto.ProductRequestDTO;
import com.market.Dto.ProductResponseDTO;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
public interface IProductController {

    // Admin - Ürün ekleme
    @PostMapping("/add")
    ResponseEntity<ProductResponseDTO> addProduct(@RequestBody @Valid ProductRequestDTO productdto);

    // Admin - Ürün güncelleme
    @PutMapping("/update/{id}")
    ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductRequestDTO dto
    );

    // Admin - Ürün silme
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Long id);

    // Herkes - Tüm ürünleri listele
    @GetMapping
    ResponseEntity<List<ProductResponseDTO>> getAllProducts();
}
