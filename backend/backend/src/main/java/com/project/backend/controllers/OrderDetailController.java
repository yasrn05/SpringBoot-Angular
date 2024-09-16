package com.project.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.dtos.OrderDetailDTO;
import com.project.backend.models.OrderDetail;
import com.project.backend.responses.OrderDetailResponse;
import com.project.backend.services.OrderDetailService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    // Thêm mới order detail
    @PostMapping
    public ResponseEntity<?> createOrderDetail(
            @Valid @RequestBody OrderDetailDTO orderDetailDTO) throws Exception {
        OrderDetail orderDetail = orderDetailService.createOrderDetail(orderDetailDTO);
        return ResponseEntity.ok().body(OrderDetailResponse.fromOrderDetail(orderDetail));
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<?> getOrderDetail(
            @Valid @PathVariable("order_id") Long id) throws Exception {
        OrderDetail orderDetail = orderDetailService.getOrderDetail(id);
        return ResponseEntity.ok(OrderDetailResponse.fromOrderDetail(orderDetail));
    }

    // Lấy danh sách orderdetail của một order
    @GetMapping("/order/{order_id}")
    public ResponseEntity<?> getOrderDetials(
            @Valid @PathVariable("order_id") Long orderId) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);
        List<OrderDetailResponse> orderDetailResponses = orderDetails
                .stream()
                .map(OrderDetailResponse::fromOrderDetail)
                .toList();
        return ResponseEntity.ok(orderDetailResponses);
    }

    // Sửa orderdetail
    @PutMapping("/{order_id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable("order_id") Long orderId,
            @Valid @RequestBody OrderDetailDTO newOrderDetailData) {
        return ResponseEntity.ok("Update orderdetail with id" + orderId + " , new data" + newOrderDetailData);
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<Void> deleteOrderDetail(
            @Valid @PathVariable("order_id") Long orderId) {
        return ResponseEntity.noContent().build();
    }
}
