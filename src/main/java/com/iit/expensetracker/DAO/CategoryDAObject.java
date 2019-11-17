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
        String sql = "INSERT INTO category(category_id, user_id, category_name, category_type, category_limit) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, category.getCategory_id(), category.getUser_id(), category.getCategory_name(), category.getCategory_type().toString(), category.getCategory_limit());
    }

    public List<Category> getAllCategoriesForAUserByUserId(String user_id){
        String sql = "SELECT * FROM category WHERE user_id=?";
        return jdbcTemplate.query(sql, new String[]{user_id}, new CategoryMapper());
    }

    public Category retrieveCategoryByCategoryId(String category_id){
        String sql = "SELECT * FROM category WHERE category_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new String[]{category_id}, new CategoryMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Category> getAllCategories(){
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new CategoryMapper());
    }
    public void categoryDeleteById(String category_id){
        String sql = "DELETE FROM category WHERE category_id=?";
        jdbcTemplate.update(sql,category_id);
    }
    public void categoryEditById(Category category){
        String sql = "UPDATE category SET category_id=?,user_id=?,category_name=?,category_type=?,category_limit=? WHERE category_id=?";
        jdbcTemplate.update(sql, category.getCategory_id(), category.getUser_id(), category.getCategory_name(), category.getCategory_type().toString(), category.getCategory_limit(), category.getCategory_id());
    }
}
