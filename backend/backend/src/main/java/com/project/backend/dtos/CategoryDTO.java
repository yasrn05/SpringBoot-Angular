package com.project.backend.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data // Get, set, hàm khởi tạo, toString
public class CategoryDTO {
    @NotEmpty(message = "Category's name can not empty")
    private String name;
}
