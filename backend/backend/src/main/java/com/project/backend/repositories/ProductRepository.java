package com.project.backend.repositories;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String title);

    Page<Product> findAll(Pageable pageable); // Phân trang
}
