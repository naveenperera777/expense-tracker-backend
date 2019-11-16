package com.iit.expensetracker.DAO;

import com.iit.expensetracker.DataMapper.CategoryMapper;
import com.iit.expensetracker.Model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDAObject {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserDAObject.class);

    public CategoryDAObject(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveCategory(Category category){
        String sql = "INSERT INTO category(categoryId, userId, category, type, exp_limit) VALUES(?,?,?,?,?)";
        logger.info("category DAO {} " , category.toString());
        jdbcTemplate.update(sql, category.getCategoryId(), category.getUserId(), category.getCategoryName(), category.getType().toString(), category.getLimit());
    }
    public Category getCategoryById(String categoryId){
        String sql = "SELECT * FROM category WHERE categoryId=?";
        try {
            return jdbcTemplate.queryForObject(sql, new String[]{categoryId}, new CategoryMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Category> getAllCategoriesByUserId(String userId){
        String sql = "SELECT * FROM category WHERE userId=?";
        logger.info("Category DAO get all categories for a user {}", userId);
        return jdbcTemplate.query(sql, new String[]{userId}, new CategoryMapper());
    }
    public List<Category> getAllCategories(){
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new CategoryMapper());
    }
    public void deleteCategoryById(String categoryId){
        String sql = "DELETE FROM category WHERE categoryId=?";
        jdbcTemplate.update(sql,categoryId);
    }
    public void editCategory(Category category){
        String sql = "UPDATE category SET categoryId=?,userId=?,category=?,type=?,exp_limit=? WHERE categoryId=?";
        jdbcTemplate.update(sql, category.getCategoryId(), category.getUserId(), category.getCategoryName(), category.getType().toString(), category.getLimit(), category.getCategoryId());
    }
}
