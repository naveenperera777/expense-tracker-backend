package com.iit.expensetracker.DAO;

import com.iit.expensetracker.DataMapper.UserDataMapper;
import com.iit.expensetracker.Model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(UserModel userModel){
        String sql = "INSERT INTO user(userId , firstName , lastName , email , city) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql , userModel.getUserId(), userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(), userModel.getCity());
    }

    public UserModel getUserById(String userId){
        String sql = "SELECT * FROM user WHERE userId=?";
        try {
            return jdbcTemplate.queryForObject(sql, new String[]{userId}, new UserDataMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List getAllUsers(){
        String sql = "SELECT * from user";
        return jdbcTemplate.query(sql, new UserDataMapper());
    }

    public void editUserById(UserModel userModel){
        String sql = "UPDATE user SET userId=?, firstName=?, lastName=?, email=?, city=? WHERE userId=?";
        jdbcTemplate.update(sql,userModel.getUserId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail(),userModel.getCity(), userModel.getUserId());
    }

    public void deleteByUserId(String userId){
        String sql = "DELETE FROM user WHERE userId=?";
        jdbcTemplate.update(sql,userId);
    }
}
