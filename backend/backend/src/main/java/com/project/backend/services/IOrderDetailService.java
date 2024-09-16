package com.project.backend.services;

import java.util.List;

import com.project.backend.dtos.OrderDetailDTO;
import com.project.backend.models.OrderDetail;

public interface IOrderDetailService {
    OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    OrderDetail getOrderDetail(Long id) throws Exception;

    OrderDetail updateOrderDetail(Long id, OrderDetailDTO newOrderDetailData) throws Exception;

    void deletedOrderDetail(Long id);

    List<OrderDetail> findByOrderId(Long orderId);
}
