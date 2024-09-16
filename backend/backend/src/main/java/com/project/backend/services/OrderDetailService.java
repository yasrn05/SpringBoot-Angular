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
                                                () -> new DataNotFoundException("Cannot find order with id: "
                                                                + oderDetailDTO.getOrderId()));
                Product product = productRepository.findById(oderDetailDTO.getProductId())
                                .orElseThrow(
                                                () -> new DataNotFoundException(
                                                                "Cannot find product with id: "
                                                                                + oderDetailDTO.getProductId()));
                OrderDetail orderDetail = OrderDetail.builder()
                                .order(order)
                                .product(product)
                                .price(oderDetailDTO.getPrice())
                                .numberOfProducts(oderDetailDTO.getNumberOfProducts())
                                .totalMoney(oderDetailDTO.getTotalMoney())
                                .color(oderDetailDTO.getColor())
                                .build();
                return orderDetailRepository.save(orderDetail);
        }

        @Override
        public OrderDetail getOrderDetail(Long id) throws Exception {
                return orderDetailRepository.findById(id)
                                .orElseThrow(() -> new DataNotFoundException("Cannot find oderdetail with id: " + id));
        }

        @Override
        public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws Exception {
                // Check orderDetail
                OrderDetail existingOrderDetail = orderDetailRepository.findById(id)
                                .orElseThrow(() -> new DataNotFoundException("Cannot find OrderDetail with id: " + id));
                Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderId())
                                .orElseThrow(
                                                () -> new DataNotFoundException("Cannot find Order with id: "
                                                                + orderDetailDTO.getOrderId()));
                Product existingProduct = productRepository.findById(orderDetailDTO.getProductId())
                                .orElseThrow(() -> new DataNotFoundException(
                                                "Cannot find Prodcut with id: " + orderDetailDTO.getProductId()));
                existingOrderDetail.setPrice(orderDetailDTO.getPrice());
                existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
                existingOrderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
                existingOrderDetail.setColor(orderDetailDTO.getColor());
                existingOrderDetail.setOrder(existingOrder);
                existingOrderDetail.setProduct(existingProduct);
                return orderDetailRepository.save(existingOrderDetail);
        }

        @Override
        public void deletedById(Long id) {
                orderDetailRepository.deleteById(id);
        }

        @Override
        public List<OrderDetail> findByOrderId(Long orderId) {
                return orderDetailRepository.findByOrderId(orderId);
        }

}
