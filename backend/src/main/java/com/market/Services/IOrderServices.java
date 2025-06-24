package com.market.Services;

import com.market.Dto.OrderRequestDTO;
import com.market.Dto.OrderResponseDTO;
import com.market.Enums.OrderStatus;

import java.util.List;

public interface IOrderServices {

    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);

    List<OrderResponseDTO> getOrdersForCurrentUser();
    OrderResponseDTO getOrderById(Long orderId);
    OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus newStatus);

}
