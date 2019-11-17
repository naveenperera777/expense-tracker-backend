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
    public Object saveNewCategory(CategoryDTObject categoryDTObject) {
        Category category = copyDtoToModel(categoryDTObject);
        categoryDAObject.saveCategory(category);
        return new Response(ResponseMessage.SUCCESS, category);

    }

    @Override
    public Object getAllCategoriesForAUserByUserId(String userId) {
        logger.info("user {}", userId);
        User user = userDAObject.getUserById(userId);
        if (user == null)
            return new Response(ResponseMessage.NO_RECORD, "User Not Found in Database");
        List<Category> UserCategoryList = categoryDAObject.getAllCategoriesForAUserByUserId(userId);
        if (UserCategoryList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No Categories found in Database for this user");
        return new Response(ResponseMessage.SUCCESS, UserCategoryList);
    }

    @Override
    public Object retrieveCategoryByCategoryId(String categoryId) {
        Category category = categoryDAObject.retrieveCategoryByCategoryId(categoryId);
        if (category == null){
            return new Response(ResponseMessage.NO_RECORD, HttpStatus.NOT_FOUND);
        }
        return new Response(ResponseMessage.SUCCESS, category);
    }



    @Override
    public Object getAllCategories() {
        List<Category> categoryList = categoryDAObject.getAllCategories();
        if (categoryList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No categories found!");
        return new Response(ResponseMessage.SUCCESS,categoryList);
    }

    @Override
    public Object categoryDeleteById(String category_id) {
        Category category = categoryDAObject.retrieveCategoryByCategoryId(category_id);
        if (category == null)
            return new Response(ResponseMessage.NO_RECORD, HttpStatus.NOT_FOUND);
        categoryDAObject.categoryDeleteById(category_id);
        return new Response(ResponseMessage.SUCCESS, HttpStatus.OK);
    }

    @Override
    public Object categoryEditById(CategoryDTObject categoryDTObject, String category_id) {
        Category category = categoryDAObject.retrieveCategoryByCategoryId(category_id);
        if (category == null)
            return new Response(ResponseMessage.NO_RECORD, HttpStatus.NOT_FOUND);
        Category editCategory = new Category();
        editCategory.setCategory_id(category_id);
        editCategory.setCategory_name(categoryDTObject.getCategory_name());
        editCategory.setUser_id(categoryDTObject.getUser_id());
        editCategory.setCategory_type(categoryDTObject.getCategory_type());
        editCategory.setCategory_limit(categoryDTObject.getCategory_limit());
        categoryDAObject.categoryEditById(editCategory);

        return new Response(ResponseMessage.SUCCESS , editCategory);
    }

    public Category copyDtoToModel(CategoryDTObject categoryDTObject){
        Category category = new Category();
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        category.setUser_id(categoryDTObject.getUser_id());
        category.setCategory_id(uuid);
        category.setCategory_name(categoryDTObject.getCategory_name());
        category.setCategory_type(categoryDTObject.getCategory_type());
        category.setCategory_limit(categoryDTObject.getCategory_limit());
        return category;
    }
}
