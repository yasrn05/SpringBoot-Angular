package com.project.backend.services;

import java.util.List;

import com.project.backend.dtos.CategoryDTO;
import com.project.backend.models.Category;

public interface ICategoryService {
    Category createCategory(CategoryDTO category);

    Category getCategoryById(long id);

    List<Category> getAllCategories();

    Category updateCategory(long categoryId, CategoryDTO category);

    void deleteCategory(long id);
}
