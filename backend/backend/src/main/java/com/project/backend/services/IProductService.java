package com.project.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.backend.dtos.ProductDTO;
import com.project.backend.dtos.ProductImageDTO;
import com.project.backend.models.Product;
import com.project.backend.models.ProductImage;
import com.project.backend.responses.ProductResponse;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws Exception;

    Product getProductById(long id) throws Exception;

    Page<ProductResponse> getAllProducts(PageRequest pageRequest);

    Product updateProduct(long id, ProductDTO productDTO) throws Exception;

    void deleteProduct(long id);

    boolean existsByName(String name);

    ProductImage createProductImage(long productId, ProductImageDTO productImageDTO)
            throws Exception;
}
