package com.market.Services.impl;

import com.market.Dto.CartItemRequestDto;
import com.market.Dto.CartItemResponseDto;
import com.market.Entities.*;
import com.market.Repository.*;
import com.market.Services.ICartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplCartService implements ICartServices {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // --- Sepete ürün ekle ---
    @Override
    public void addToCart(Long userId, CartItemRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(requestDto.getQuantity());

        cart.addItem(item);
        cartRepository.save(cart);
    }

    // --- Sepetten ürün sil ---
    @Override
    public void removeFromCart(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));

        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ürün sepette bulunamadı"));

        cart.removeItem(itemToRemove);
        cartRepository.save(cart);
    }

    // --- Ürün adeti güncelle ---
    @Override
    public void updateCartItemQuantity(Long userId, Long productId, int newQuantity) {
        if (newQuantity <= 0) {
            throw new IllegalArgumentException("Adet 1’den küçük olamaz.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ürün sepette bulunamadı"));

        item.setQuantity(newQuantity);
        cartRepository.save(cart);
    }

    // --- Sepeti tamamen temizle ---
    @Override
    public void clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));

        cart.getItems().clear();
        cartRepository.save(cart);
    }

    // --- Sepetteki ürünleri getir ---
    @Override
    public List<CartItemResponseDto> getCartItems(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));

        List<CartItemResponseDto> responseList = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            CartItemResponseDto dto = new CartItemResponseDto();
            dto.setProductId(product.getId());
            dto.setProductName(product.getName());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(product.getPrice());
            dto.setTotalPrice(product.getPrice() * item.getQuantity());
            dto.setImageUrl(product.getImageUrl()); // imageUrl alanı Product entity'de varsa
            responseList.add(dto);
        }

        return responseList;
    }
}
