package com.iit.expensetracker.Service;

import com.iit.expensetracker.Dto.CategoryDto;

public interface CategoryService {
    Object saveCategory(CategoryDto categoryDto);
    Object getCategoryById(String categoryId);
    Object getAllCategories();
    Object deleteCategoryById(String catrgoryId);
    Object editCategoryById(CategoryDto categoryDto, String categoryId);
}
