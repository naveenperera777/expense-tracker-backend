package com.iit.expensetracker.Service;

import com.iit.expensetracker.Dto.CategoryDto;

public interface CategoryService {
    Object saveCategory(CategoryDto categoryDto);
    Object getCategoryById(String categoryId);
    Object getAllCategories();
}
