package com.iit.expensetracker.DataMapper;
import com.iit.expensetracker.Model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataMapper implements RowMapper<UserModel> {

    private static final Logger logger = LoggerFactory.getLogger(UserDataMapper.class);

    @Override
    public UserModel mapRow(ResultSet resultSet, int i) throws SQLException {
        UserModel userModel = new UserModel();
        userModel.setUserId(resultSet.getString("userId"));
        userModel.setFirstName(resultSet.getString("firstName"));
        userModel.setLastName(resultSet.getString("lastName"));
        userModel.setEmail(resultSet.getString("email"));
        userModel.setCity(resultSet.getString("city"));

        return userModel;
    }


}
