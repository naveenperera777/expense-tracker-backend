package com.iit.expensetracker.DAO;

import com.iit.expensetracker.DataMapper.CategoryDataMapper;
import com.iit.expensetracker.Model.CategoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public CategoryModel getCategoryById(String categoryId){
        String sql = "SELECT * FROM category WHERE categoryId=?";
        try {
            return jdbcTemplate.queryForObject(sql, new String[]{categoryId}, new CategoryDataMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public List<CategoryModel> getAllCategories(){
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new CategoryDataMapper());
    }
    public void deleteCategoryById(String categoryId){
        String sql = "DELETE FROM category WHERE categoryId=?";
        jdbcTemplate.update(sql,categoryId);
    }
    public void editCategory(CategoryModel categoryModel){
        String sql = "UPDATE category SET categoryId=?,userId=?,category=?,type=?,exp_limit=? WHERE categoryId=?";
        jdbcTemplate.update(sql, categoryModel.getCategoryId(), categoryModel.getUserId(), categoryModel.getCategoryName(), categoryModel.getType().toString(), categoryModel.getLimit(), categoryModel.getCategoryId());
    }
}
