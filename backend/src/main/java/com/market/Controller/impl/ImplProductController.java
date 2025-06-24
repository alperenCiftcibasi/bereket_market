package com.market.Controller.impl;

import com.market.Controller.IProductController;
import com.market.Dto.ProductRequestDTO;
import com.market.Dto.ProductResponseDTO;
import com.market.Services.IProductServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ImplProductController implements IProductController {

    @Autowired
    private IProductServices productService;

    // Admin - Ürün ekleme
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody @Valid ProductRequestDTO productdto) {
        return ResponseEntity.ok(productService.save(productdto)); 
        }

    // Admin - Ürün güncelleme
    @Override
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductRequestDTO dto
    ) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    // Admin - Ürün silme
    @Override
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Ürün silindi");
    }

    // Herkes - Ürünleri listele
    @Override
    @GetMapping(path ="/get")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }
}
