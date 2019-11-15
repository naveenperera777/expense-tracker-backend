package com.iit.expensetracker.DAO;

import com.iit.expensetracker.Model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
}
