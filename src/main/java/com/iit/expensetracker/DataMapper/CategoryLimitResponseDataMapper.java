package com.iit.expensetracker.DataMapper;
import com.iit.expensetracker.Dto.CategoryLimitResponseDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryLimitResponseDataMapper implements RowMapper<CategoryLimitResponseDto> {

    @Override
    public CategoryLimitResponseDto mapRow(ResultSet resultSet, int i) throws SQLException {
        CategoryLimitResponseDto categoryLimitResponseDto = new CategoryLimitResponseDto();
        categoryLimitResponseDto.setUserId(resultSet.getString("userId"));
        categoryLimitResponseDto.setCategoryId(resultSet.getString("categoryId"));
        categoryLimitResponseDto.setCategory(resultSet.getString("category"));
        categoryLimitResponseDto.setType(resultSet.getString("type"));
        categoryLimitResponseDto.setLimit(resultSet.getDouble("exp_limit"));
        categoryLimitResponseDto.setTotalExpenes(resultSet.getDouble("totalAmount"));

        return categoryLimitResponseDto;
    }
}
