package com.iit.expensetracker.Service;

import com.iit.expensetracker.Dto.CategoryDTObject;

public interface CategoryService {
    Object saveCategory(CategoryDTObject categoryDTObject);
    Object getCategoryById(String categoryId);
    Object getAllCategoriesByUserId(String userId);
    Object getAllCategories();
    Object deleteCategoryById(String catrgoryId);
    Object editCategoryById(CategoryDTObject categoryDTObject, String categoryId);
}
