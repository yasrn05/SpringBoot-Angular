package com.project.backend.services;

import java.util.List;

import com.project.backend.dtos.OrderDTO;
import com.project.backend.responses.OrderResponse;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws Exception;

    OrderResponse getOrder(Long id);

    OrderResponse updateOrder(Long id, OrderDTO orderDTO);

    void deletedOrder(Long id);

    List<OrderResponse> getAllOrder(Long userId);
}
