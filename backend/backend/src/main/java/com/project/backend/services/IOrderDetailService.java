package com.project.backend.services;

import java.util.List;

import com.project.backend.dtos.OrderDetailDTO;
import com.project.backend.models.OrderDetail;

public interface IOrderDetailService {
    OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    OrderDetail getOrderDetail(Long id);

    OrderDetail updateOrderDetail(Long id, OrderDetailDTO newOrderDetailData);

    void deletedOrderDetail(Long id);

    List<OrderDetail> getOrderDetails(Long orderId);
}