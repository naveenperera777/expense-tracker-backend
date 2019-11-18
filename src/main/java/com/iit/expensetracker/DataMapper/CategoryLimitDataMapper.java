package com.iit.expensetracker.DataMapper;

import com.iit.expensetracker.Dto.CategoryLimitResponseDTObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoryLimitDataMapper implements RowMapper<CategoryLimitResponseDTObject> {

    @Override
    public CategoryLimitResponseDTObject mapRow(ResultSet resultSet, int i) throws SQLException {
        CategoryLimitResponseDTObject categoryLimitResponseDto = new CategoryLimitResponseDTObject();
        categoryLimitResponseDto.setUserId(resultSet.getString("user_id"));
        categoryLimitResponseDto.setCategoryId(resultSet.getString("category_id"));
        categoryLimitResponseDto.setCategory(resultSet.getString("category_name"));
        categoryLimitResponseDto.setType(resultSet.getString("category_type"));
        categoryLimitResponseDto.setLimit(resultSet.getDouble("category_limit"));
        categoryLimitResponseDto.setTotalExpenes(resultSet.getDouble("transaction_amount"));
        categoryLimitResponseDto.setTotalAmount(resultSet.getDouble("totalAmount"));
        return categoryLimitResponseDto;
    }
}