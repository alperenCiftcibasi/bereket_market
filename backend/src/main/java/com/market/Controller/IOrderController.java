package com.market.Controller;

import com.market.Dto.OrderRequestDTO;
import com.market.Dto.OrderResponseDTO;
import com.market.Enums.OrderStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IOrderController {

    @PostMapping("/api/orders")
    ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO);

    @GetMapping("/api/orders/me")
    ResponseEntity<List<OrderResponseDTO>> getMyOrders();
    @GetMapping("/api/orders/{id}")
    ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id);

    @PutMapping("/api/orders/{id}/status")
    ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status);

}
