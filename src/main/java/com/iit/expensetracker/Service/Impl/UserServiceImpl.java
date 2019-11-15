package com.iit.expensetracker.Service.Impl;

import com.iit.expensetracker.DAO.UserDAO;
import com.iit.expensetracker.Dto.UserDto;
import com.iit.expensetracker.Model.UserModel;
import com.iit.expensetracker.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void saveUser(UserDto userDto) {
        UserModel userModel = new UserModel();
        userModel.setUserId("1");
        userModel.setFirstName(userDto.getFirstName());
        userModel.setLastName(userDto.getLastName());
        userModel.setEmail(userDto.getEmail());
        userModel.setCity(userDto.getCity());
        logger.info("saving user {}",userModel.toString());
        userDAO.saveUser(userModel);
    }
}
