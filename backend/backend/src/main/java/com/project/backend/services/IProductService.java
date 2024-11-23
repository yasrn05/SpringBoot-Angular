package com.project.backend.services;

import com.project.backend.dtos.ProductDTO;
import com.project.backend.dtos.ProductImageDTO;
import com.project.backend.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.project.backend.models.*;

import java.util.List;

public interface IProductService {
        Product createProduct(ProductDTO productDTO) throws Exception;

        Product getProductById(long id) throws Exception;

        public Page<ProductResponse> getAllProducts(String keyword,
                        Long categoryId, PageRequest pageRequest);

        Product updateProduct(long id, ProductDTO productDTO) throws Exception;

        void deleteProduct(long id);

        boolean existsByName(String name);

        ProductImage createProductImage(
                        Long productId,
                        ProductImageDTO productImageDTO) throws Exception;

        List<Product> findProductsByIds(List<Long> productIds);
}
