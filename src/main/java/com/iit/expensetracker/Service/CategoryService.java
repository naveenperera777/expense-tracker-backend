package com.iit.expensetracker.Service;

import com.iit.expensetracker.Dto.CategoryDTObject;

public interface CategoryService {
    Object saveNewCategory(CategoryDTObject categoryDTObject);
    Object getAllCategoriesForAUserByUserId(String userId);
    Object retrieveCategoryByCategoryId(String categoryId);
    Object getAllCategories();
    Object categoryDeleteById(String category_id);
    Object categoryEditById(CategoryDTObject categoryDTObject, String category_id);
}
