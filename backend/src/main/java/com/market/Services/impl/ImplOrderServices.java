package com.market.Services.impl;

import com.market.Enums.OrderStatus; 
import com.market.Dto.OrderMapper;
import com.market.Dto.OrderRequestDTO;
import com.market.Dto.OrderResponseDTO;
import com.market.Entities.Order;
import com.market.Entities.Product;
import com.market.Entities.User;
import com.market.Repository.OrderRepository;
import com.market.Repository.ProductRepository;
import com.market.Services.IOrderServices;
import com.market.Services.IUserServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplOrderServices implements IOrderServices {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final IUserServices userService;

    public ImplOrderServices(OrderRepository orderRepository,
                             ProductRepository productRepository,
                             IUserServices userService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        User user = userService.getCurrentUser();

        List<Long> productIds = orderRequestDTO.getItems().stream()
                .map(item -> item.getProductId())
                .collect(Collectors.toList());

        List<Product> products = productRepository.findAllById(productIds);

        Order order = OrderMapper.toEntity(orderRequestDTO, user, products);
        orderRepository.save(order);

        return OrderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDTO> getOrdersForCurrentUser() {
        User user = userService.getCurrentUser();
        List<Order> orders = orderRepository.findByUser(user);
        return orders.stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        User user = userService.getCurrentUser();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Bu siparişi görüntüleme yetkiniz yok");
        }

        return OrderMapper.toDto(order);
    }

    @Override
    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));

        order.setStatus(newStatus);
        orderRepository.save(order);

        return OrderMapper.toDto(order);
    }
    
}
