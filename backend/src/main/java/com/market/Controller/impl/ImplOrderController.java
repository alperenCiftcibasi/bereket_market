package com.market.Controller.impl;

import com.market.Controller.IOrderController;
import com.market.Dto.OrderRequestDTO;
import com.market.Dto.OrderResponseDTO;
import com.market.Enums.OrderStatus;
import com.market.Services.IOrderServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ImplOrderController implements IOrderController {

    private final IOrderServices orderService;

    public ImplOrderController(IOrderServices orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.ok(orderService.createOrder(orderRequestDTO));
    }

    @Override
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders() {
        return ResponseEntity.ok(orderService.getOrdersForCurrentUser());
    }

    @Override
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Override
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Long id,
                                                              @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }
}
