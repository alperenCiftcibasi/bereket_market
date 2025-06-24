package com.market.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemDTO {

    @NotNull(message = "Ürün ID boş olamaz.")
    private Long productId;

    @Min(value = 1, message = "Ürün adedi en az 1 olmalıdır.")
    private int quantity;

    // getter - setter

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
