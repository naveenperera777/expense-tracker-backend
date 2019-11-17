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
        category.setCategory_id(resultSet.getString("category_id"));
        category.setUser_id(resultSet.getString("user_id"));
        category.setCategory_name(resultSet.getString("category_name"));
        category.setCategory_type(CategoryEnum.valueOf(resultSet.getString("category_type")));
        category.setCategory_limit(resultSet.getDouble("category_limit"));
        return category;
    }
}
