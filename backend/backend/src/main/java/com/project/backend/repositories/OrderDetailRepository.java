package com.project.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
