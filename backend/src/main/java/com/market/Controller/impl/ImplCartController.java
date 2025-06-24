package com.market.Controller.impl;

import com.market.Dto.CartItemRequestDto;
import com.market.Dto.CartItemResponseDto;
import com.market.Entities.User;
import com.market.Repository.UserRepository;
import com.market.Services.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ImplCartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    // Sepete ürün ekleme
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            Authentication authentication,
            @RequestBody @Valid CartItemRequestDto requestDto
    ) {
        User user = getUserFromAuth(authentication);
        cartService.addToCart(user.getId(), requestDto);
        return ResponseEntity.ok("Ürün sepete eklendi.");
    }

    // Sepetten ürün silme
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(
            Authentication authentication,
            @RequestParam Long productId
    ) {
        User user = getUserFromAuth(authentication);
        cartService.removeFromCart(user.getId(), productId);
        return ResponseEntity.ok("Ürün sepetten silindi.");
    }

    // Sepeti listeleme
    @GetMapping
    public ResponseEntity<List<CartItemResponseDto>> getCartItems(Authentication authentication) {
        User user = getUserFromAuth(authentication);
        List<CartItemResponseDto> items = cartService.getCartItems(user.getId());
        return ResponseEntity.ok(items);
    }

    // Ürün adeti güncelleme
    @PutMapping("/update")
    public ResponseEntity<String> updateCartItemQuantity(
            Authentication authentication,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        User user = getUserFromAuth(authentication);
        cartService.updateCartItemQuantity(user.getId(), productId, quantity);
        return ResponseEntity.ok("Ürün adeti güncellendi.");
    }

    // Sepeti tamamen temizleme
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(Authentication authentication) {
        User user = getUserFromAuth(authentication);
        cartService.clearCart(user.getId());
        return ResponseEntity.ok("Sepet başarıyla temizlendi.");
    }

    // Ortak kullanıcı çekme metodu
    private User getUserFromAuth(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    }
}
