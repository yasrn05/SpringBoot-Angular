package com.project.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Tìm các đơn hàng của một người dùng nào đó
    List<Order> findByUserId(Long userId);
}
