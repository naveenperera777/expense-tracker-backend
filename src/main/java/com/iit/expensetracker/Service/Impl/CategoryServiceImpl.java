package com.iit.expensetracker.Service.Impl;

import com.iit.expensetracker.DAO.CategoryDAO;
import com.iit.expensetracker.Dto.CategoryDto;
import com.iit.expensetracker.Model.CategoryModel;
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
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Object saveCategory(CategoryDto categoryDto) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setUserId(categoryDto.getUserId());
        UUID uuid = UUID.randomUUID();
        categoryModel.setCategoryId(uuid.toString());
        categoryModel.setCategoryName(categoryDto.getCategoryName());
        categoryModel.setType(categoryDto.getType());
        categoryModel.setLimit(categoryDto.getLimit());
        logger.info("saving category {}",categoryModel.toString());
        categoryDAO.saveCategory(categoryModel);
        return new Response(ResponseMessage.SUCCESS, categoryModel);

    }

    @Override
    public Object getCategoryById(String categoryId) {
        CategoryModel categoryModel = categoryDAO.getCategoryById(categoryId);
        if (categoryModel == null){
            return new Response(ResponseMessage.NO_RECORD, HttpStatus.NOT_FOUND);
        }
        return new Response(ResponseMessage.SUCCESS, categoryModel);
    }

    @Override
    public Object getAllCategories() {
        List<CategoryModel> categoryList = categoryDAO.getAllCategories();
        if (categoryList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No categories found!");
        return new Response(ResponseMessage.SUCCESS,categoryList);
    }
}
