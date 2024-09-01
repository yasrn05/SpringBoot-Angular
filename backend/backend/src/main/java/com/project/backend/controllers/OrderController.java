package com.project.backend.controllers;

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
import com.project.backend.models.Order;
import com.project.backend.services.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

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
            Order orderResponse = orderService.createOrder(orderDTO);
            return ResponseEntity.ok(orderResponse);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping("/user/{user_id}")
    // Get http://localhost:8888/api/v1/orders/user/23
    public ResponseEntity<?> getOrders(
            @Valid @PathVariable("user_id") Long userId) {
        try {
            List<Order> orders = orderService.findByUserId(userId);
            return ResponseEntity.ok(orders);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping("/{id}")
    // Get http://localhost:8888/api/v1/orders/23
    public ResponseEntity<?> getOrder(
            @Valid @PathVariable("id") Long orderId) {
        try {
            Order existingOrder = orderService.getOrder(orderId);
            return ResponseEntity.ok(existingOrder);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping("/{order_id}")
    // Put http://localhost:8888/api/v1/orders/1
    // Người quản trị
    public ResponseEntity<?> uploadOrder(
            @Valid @PathVariable("order_id") Long orderId,
            @Valid @RequestBody OrderDTO orderDTO) {
        try {
            Order order = orderService.updateOrder(orderId, orderDTO);
            return ResponseEntity.ok(order);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<String> deleteOrder(
            @Valid @PathVariable("order_id") Long orderId) {
        // Xóa mềm -> cập nhật active = False
        return ResponseEntity.ok("Order deleted successfully");
    }
}
