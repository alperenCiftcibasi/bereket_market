package com.market.Dto;

import com.market.Entities.Order;
import com.market.Entities.OrderItem;
import com.market.Entities.Product;
import com.market.Entities.User;
import com.market.Enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order toEntity(OrderRequestDTO dto, User user, List<Product> products) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0;

        for (OrderItemDTO itemDTO : dto.getItems()) {
            Product product = products.stream()
                    .filter(p -> p.getId().equals(itemDTO.getProductId()))
                    .findFirst()
                    .orElse(null);

            if (product != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setQuantity(itemDTO.getQuantity());
                double subtotal = product.getPrice() * itemDTO.getQuantity();
                orderItem.setSubtotal(subtotal);
                orderItem.setOrder(order);

                totalPrice += subtotal;
                orderItems.add(orderItem);
            }
        }

        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        return order;
    }

    public static OrderResponseDTO toDto(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setStatus(order.getStatus());

        List<OrderItemDetailDTO> itemDTOs = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            OrderItemDetailDTO itemDto = new OrderItemDetailDTO();
            itemDto.setProductName(item.getProduct().getName());
            itemDto.setQuantity(item.getQuantity());
            itemDto.setSubtotal(item.getSubtotal());
            itemDTOs.add(itemDto);
        }

        dto.setItems(itemDTOs);

        return dto;
    }
}
