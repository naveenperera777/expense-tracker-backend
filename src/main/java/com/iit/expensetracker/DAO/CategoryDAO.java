package com.iit.expensetracker.DAO;

import com.iit.expensetracker.Model.CategoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CategoryDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public CategoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveCategory(CategoryModel categoryModel){
        String sql = "INSERT INTO category(categoryId, userId, category, type, exp_limit) VALUES(?,?,?,?,?)";
        logger.info("category DAO {} " ,categoryModel.toString());
        jdbcTemplate.update(sql, categoryModel.getCategoryId(), categoryModel.getUserId(), categoryModel.getCategoryName(), categoryModel.getType().toString(), categoryModel.getLimit());
    }
}
