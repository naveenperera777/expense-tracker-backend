package com.iit.expensetracker.DataMapper;
import com.iit.expensetracker.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getString("userId"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setEmail(resultSet.getString("email"));
        user.setTelephoneNumber(resultSet.getString("telephoneNumber"));
        return user;
    }


}
