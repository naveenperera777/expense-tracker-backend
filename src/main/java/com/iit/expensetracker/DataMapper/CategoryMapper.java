package com.iit.expensetracker.DataMapper;


import com.iit.expensetracker.Model.Category;
import com.iit.expensetracker.enums.CategoryEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {

    private static final Logger logger = LoggerFactory.getLogger(CategoryMapper.class);

    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setCategoryId(resultSet.getString("categoryId"));
        category.setUserId(resultSet.getString("userId"));
        category.setCategoryName(resultSet.getString("category"));
//        CategoryEnum categoryEnum = CategoryEnum.valueOf(resultSet.getString("type"));
        category.setType(CategoryEnum.valueOf(resultSet.getString("type")));
        category.setLimit(resultSet.getDouble("exp_limit"));
        return category;
    }
}
