package com.project.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long id);
}
