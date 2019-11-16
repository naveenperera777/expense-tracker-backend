package com.iit.expensetracker.DataMapper;


import com.iit.expensetracker.Model.CategoryModel;
import com.iit.expensetracker.enums.CategoryEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDataMapper implements RowMapper<CategoryModel> {

    private static final Logger logger = LoggerFactory.getLogger(CategoryDataMapper.class);

    @Override
    public CategoryModel mapRow(ResultSet resultSet, int i) throws SQLException {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategoryId(resultSet.getString("categoryId"));
        categoryModel.setUserId(resultSet.getString("userId"));
        categoryModel.setCategoryName(resultSet.getString("category"));
//        CategoryEnum categoryEnum = CategoryEnum.valueOf(resultSet.getString("type"));
        categoryModel.setType(CategoryEnum.valueOf(resultSet.getString("type")));
        categoryModel.setLimit(resultSet.getDouble("exp_limit"));
        return categoryModel;
    }
}
