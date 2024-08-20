package com.project.backend.controllers;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {
    // Thêm mới order detail
    @PostMapping
    public ResponseEntity<?> createOrderDetail(
            @Valid @RequestBody OrderDetailDTO newOrderDetail) {
        return ResponseEntity.ok("Create Order Detail");
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<?> getOrderDetail(
            @Valid @PathVariable("order_id") Long id) {
        return ResponseEntity.ok("Get Order Detail with id: " + id);
    }

    // Lấy danh sách orderdetail của một order
    @GetMapping("/order/{order_id}")
    public ResponseEntity<?> getOrderDetials(
            @Valid @PathVariable("order_id") Long orderId) {
        return ResponseEntity.ok("Get order details with orderId = " + orderId);
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
