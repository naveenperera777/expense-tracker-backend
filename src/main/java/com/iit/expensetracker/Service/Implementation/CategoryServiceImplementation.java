package com.iit.expensetracker.Service.Implementation;

import com.iit.expensetracker.DAO.CategoryDAObject;
import com.iit.expensetracker.DAO.UserDAObject;
import com.iit.expensetracker.Dto.CategoryDTObject;
import com.iit.expensetracker.Model.Category;
import com.iit.expensetracker.Model.User;
import com.iit.expensetracker.Response.Response;
import com.iit.expensetracker.Service.CategoryService;
import com.iit.expensetracker.enums.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImplementation implements CategoryService {
    private final CategoryDAObject categoryDAObject;
    private final UserDAObject userDAObject;
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImplementation.class);

    public CategoryServiceImplementation(CategoryDAObject categoryDAObject, UserDAObject userDAObject) {
        this.categoryDAObject = categoryDAObject;
        this.userDAObject = userDAObject;
    }

    @Override
    public Object saveCategory(CategoryDTObject categoryDTObject) {
        Category category = new Category();
        category.setUserId(categoryDTObject.getUserId());
        UUID uuid = UUID.randomUUID();
        category.setCategoryId(uuid.toString());
        category.setCategoryName(categoryDTObject.getCategoryName());
        category.setType(categoryDTObject.getType());
        category.setLimit(categoryDTObject.getLimit());
        logger.info("saving category {}", category.toString());
        categoryDAObject.saveCategory(category);
        return new Response(ResponseMessage.SUCCESS, category);

    }

    @Override
    public Object getCategoryById(String categoryId) {
        Category category = categoryDAObject.getCategoryById(categoryId);
        if (category == null){
            return new Response(ResponseMessage.NO_RECORD, HttpStatus.NOT_FOUND);
        }
        return new Response(ResponseMessage.SUCCESS, category);
    }

    @Override
    public Object getAllCategoriesByUserId(String userId) {
        User user = userDAObject.getUserById(userId);
        if (user == null)
            return new Response(ResponseMessage.NO_RECORD, "User Not Found!");
        List<Category> categoryList = categoryDAObject.getAllCategoriesByUserId(userId);
        if (categoryList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No Categories found for this user!");
        return new Response(ResponseMessage.SUCCESS, categoryList);
    }

    @Override
    public Object getAllCategories() {
        List<Category> categoryList = categoryDAObject.getAllCategories();
        if (categoryList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No categories found!");
        return new Response(ResponseMessage.SUCCESS,categoryList);
    }

    @Override
    public Object deleteCategoryById(String categoryId) {
        Category category = categoryDAObject.getCategoryById(categoryId);
        if (category == null)
            return new Response(ResponseMessage.NO_RECORD, HttpStatus.NOT_FOUND);
        categoryDAObject.deleteCategoryById(categoryId);
        return new Response(ResponseMessage.SUCCESS, HttpStatus.OK);
    }

    @Override
    public Object editCategoryById(CategoryDTObject categoryDTObject, String categoryId) {
        Category category = categoryDAObject.getCategoryById(categoryId);
        if (category == null)
            return new Response(ResponseMessage.NO_RECORD, HttpStatus.NOT_FOUND);
        Category editCategory = new Category();
        editCategory.setCategoryId(categoryId);
        editCategory.setCategoryName(categoryDTObject.getCategoryName());
        editCategory.setUserId(categoryDTObject.getUserId());
        editCategory.setType(categoryDTObject.getType());
        editCategory.setLimit(categoryDTObject.getLimit());
        categoryDAObject.editCategory(editCategory);

        return new Response(ResponseMessage.SUCCESS , editCategory);
    }
}
