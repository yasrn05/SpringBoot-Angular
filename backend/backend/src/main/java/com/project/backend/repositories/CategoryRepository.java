package com.project.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.models.Category;
import com.project.backend.models.OrderDetail;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
}
