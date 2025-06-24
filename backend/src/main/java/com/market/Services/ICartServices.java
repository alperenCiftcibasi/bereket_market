package com.market.Services;

import java.util.List;

import com.market.Dto.CartItemRequestDto;
import com.market.Dto.CartItemResponseDto;

public interface ICartServices {
    void addToCart(Long userId, CartItemRequestDto requestDto);
    void removeFromCart(Long userId, Long productId);
    List<CartItemResponseDto> getCartItems(Long userId);
    void updateCartItemQuantity(Long userId, Long productId, int newQuantity);
    void clearCart(Long userId);


}

