package com.project.backend.services;

import com.project.backend.dtos.ProductDTO;
import com.project.backend.dtos.ProductImageDTO;
import com.project.backend.exceptions.DataNotFoundException;
import com.project.backend.exceptions.InvalidParamException;
import com.project.backend.models.Category;
import com.project.backend.models.Product;
import com.project.backend.models.ProductImage;
import com.project.backend.repositories.CategoryRepository;
import com.project.backend.repositories.ProductImageRepository;
import com.project.backend.repositories.ProductRepository;
import com.project.backend.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
        private final ProductRepository productRepository;
        private final CategoryRepository categoryRepository;
        private final ProductImageRepository productImageRepository;

        @Override
        public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
                Category existingCategory = categoryRepository
                                .findById(productDTO.getCategoryId())
                                .orElseThrow(() -> new DataNotFoundException(
                                                "Cannot find category with id: " + productDTO.getCategoryId()));

                Product newProduct = Product.builder()
                                .name(productDTO.getName())
                                .price(productDTO.getPrice())
                                .thumbnail(productDTO.getThumbnail())
                                .description(productDTO.getDescription())
                                .category(existingCategory)
                                .build();
                return productRepository.save(newProduct);
        }

        @Override
        public Product getProductById(long productId) throws Exception {
                return productRepository.findById(productId).orElseThrow(() -> new DataNotFoundException(
                                "Cannot find product with id =" + productId));
        }

        @Override
        public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
                // Lấy danh sách sản phẩm theo trang(page) và giới hạn(limit)
                return productRepository
                                .findAll(pageRequest)
                                .map(ProductResponse::fromProduct);
        }

        @Override
        public Product updateProduct(
                        long id,
                        ProductDTO productDTO)
                        throws Exception {
                Product existingProduct = getProductById(id);
                if (existingProduct != null) {
                        // copy các thuộc tính từ DTO -> Product
                        // Có thể sử dụng ModelMapper
                        Category existingCategory = categoryRepository
                                        .findById(productDTO.getCategoryId())
                                        .orElseThrow(() -> new DataNotFoundException(
                                                        "Cannot find category with id: " + productDTO.getCategoryId()));
                        existingProduct.setName(productDTO.getName());
                        existingProduct.setCategory(existingCategory);
                        existingProduct.setPrice(productDTO.getPrice());
                        existingProduct.setDescription(productDTO.getDescription());
                        existingProduct.setThumbnail(productDTO.getThumbnail());
                        return productRepository.save(existingProduct);
                }
                return null;

        }

        @Override
        public void deleteProduct(long id) {
                Optional<Product> optionalProduct = productRepository.findById(id);
                optionalProduct.ifPresent(productRepository::delete);
        }

        @Override
        public boolean existsByName(String name) {
                return productRepository.existsByName(name);
        }

        @Override
        public ProductImage createProductImage(
                        Long productId,
                        ProductImageDTO productImageDTO) throws Exception {
                Product existingProduct = productRepository
                                .findById(productId)
                                .orElseThrow(() -> new DataNotFoundException(
                                                "Cannot find product with id: " + productImageDTO.getProductId()));
                ProductImage newProductImage = ProductImage.builder()
                                .product(existingProduct)
                                .imageUrl(productImageDTO.getImageUrl())
                                .build();
                // Ko cho insert quá 5 ảnh cho 1 sản phẩm
                int size = productImageRepository.findByProductId(productId).size();
                if (size >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
                        throw new InvalidParamException(
                                        "Number of images must be <= "
                                                        + ProductImage.MAXIMUM_IMAGES_PER_PRODUCT);
                }
                return productImageRepository.save(newProductImage);
        }
}
