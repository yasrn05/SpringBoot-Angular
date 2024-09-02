package com.project.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.backend.dtos.OrderDetailDTO;
import com.project.backend.exceptions.DataNotFoundException;
import com.project.backend.models.Order;
import com.project.backend.models.OrderDetail;
import com.project.backend.models.Product;
import com.project.backend.repositories.OrderDetailRepository;
import com.project.backend.repositories.OrderRepository;
import com.project.backend.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO oderDetailDTO) throws Exception {
        // Check order & product
        Order order = orderRepository.findById(oderDetailDTO.getOrderId())
                .orElseThrow(
                        () -> new DataNotFoundException("Cannot find order with id: " + oderDetailDTO.getOrderId()));
        Product product = productRepository.findById(oderDetailDTO.getProductId())
                .orElseThrow(
                        () -> new DataNotFoundException(
                                "Cannot find product with id: " + oderDetailDTO.getProductId()));
        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .numberOfProducts(oderDetailDTO.getNumberOfProducts())
                .totalMoney(oderDetailDTO.getTotalMoney())
                .color(oderDetailDTO.getColor())
                .build();
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetail(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderDetail'");
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO newOrderDetailData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrderDetail'");
    }

    @Override
    public void deletedOrderDetail(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletedOrderDetail'");
    }

    @Override
    public List<OrderDetail> getOrderDetails(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderDetails'");
    }

}
