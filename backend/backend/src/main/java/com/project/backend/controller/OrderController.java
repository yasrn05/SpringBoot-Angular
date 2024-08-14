package com.project.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.dtos.OrderDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    @PostMapping("")
    public ResponseEntity<?> createOrder(
            @RequestBody @Valid OrderDTO orderDTO,
            BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            return ResponseEntity.ok("Create order successfully");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping("/{user_id}")
    // Get http://localhost:8888/api/v1/orders/23
    public ResponseEntity<?> getOrders(
            @Valid @PathVariable Long user_id) {
        try {
            return ResponseEntity.ok("Danh sách order của user bằng user_id");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping("/{order_id}")
    // Put http://localhost:8888/api/v1/orders/1
    // Người quản trị
    public ResponseEntity<?> uploadOrder(
            @Valid @PathVariable Long order_id,
            @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok("Update order");
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<String> deleteOrder(
            @Valid @PathVariable Long order_id) {
        // Xóa mềm -> cập nhật active = False
        return ResponseEntity.ok("Order deleted successfully");
    }
}
