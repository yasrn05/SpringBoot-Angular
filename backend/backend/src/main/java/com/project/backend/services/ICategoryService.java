package com.project.backend.services;

import java.util.List;

import com.project.backend.models.Category;

public interface ICategoryService {
    Category createCategory(Category category);

    Category getCategoryById(long id);

    List<Category> getAllCategories();

    Category updateCategory(long categoryId, Category category);

    void deleteCategory(long id);
}
