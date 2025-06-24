package com.market.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class OrderRequestDTO {

    @NotEmpty(message = "Sipariş en az bir ürün içermelidir.")
    private List<@Valid OrderItemDTO> items;

    // getter - setter

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
