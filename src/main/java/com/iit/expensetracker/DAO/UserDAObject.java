package com.iit.expensetracker.DAO;

import com.iit.expensetracker.DataMapper.UserMapper;
import com.iit.expensetracker.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserDAObject {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserDAObject.class);

    public UserDAObject(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserById(String userId){
        String sql = "SELECT * FROM user WHERE userId=?";
        try {
            return jdbcTemplate.queryForObject(sql, new String[]{userId}, new UserMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void saveNewUser(User user){
        String sql = "INSERT INTO user(userId , firstName , lastName , email , telephoneNumber) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql , user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getTelephoneNumber());
    }

    public List getAllUsers(){
        String sql = "SELECT * from user";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public void editUserById(User user){
        String sql = "UPDATE user SET userId=?, firstName=?, lastName=?, email=?, telephoneNumber=? WHERE userId=?";
        jdbcTemplate.update(sql, user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getTelephoneNumber(), user.getUserId());
    }

    public void deleteByUserId(String userId){
        String sql = "DELETE FROM user WHERE userId=?";
        jdbcTemplate.update(sql,userId);
    }
}
