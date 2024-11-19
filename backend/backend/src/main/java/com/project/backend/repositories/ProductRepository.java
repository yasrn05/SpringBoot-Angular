package com.project.backend.repositories;

import com.project.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    @SuppressWarnings("null")
    Page<Product> findAll(Pageable pageable);// phân trang
}
