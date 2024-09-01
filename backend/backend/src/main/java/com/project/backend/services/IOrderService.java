package com.project.backend.services;

import java.util.List;

import com.project.backend.dtos.OrderDTO;
import com.project.backend.models.Order;

public interface IOrderService {
    Order createOrder(OrderDTO orderDTO) throws Exception;

    Order getOrder(Long id);

    Order updateOrder(Long id, OrderDTO orderDTO);

    void deletedOrder(Long id);

    List<Order> findByUserId(Long userId);
}
