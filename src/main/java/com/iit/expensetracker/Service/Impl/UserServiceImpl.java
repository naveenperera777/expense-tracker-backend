package com.iit.expensetracker.Service.Impl;

import com.iit.expensetracker.DAO.UserDAO;
import com.iit.expensetracker.Dto.UserDto;
import com.iit.expensetracker.Model.UserModel;
import com.iit.expensetracker.Response.Response;
import com.iit.expensetracker.Service.UserService;
import com.iit.expensetracker.enums.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Object saveUser(UserDto userDto) {
        UserModel userModel = new UserModel();
        UUID uuid = UUID.randomUUID();
        userModel.setUserId(uuid.toString());
        userModel.setFirstName(userDto.getFirstName());
        userModel.setLastName(userDto.getLastName());
        userModel.setEmail(userDto.getEmail());
        userModel.setCity(userDto.getCity());
        logger.info("saving user {}",userModel.toString());
        userDAO.saveUser(userModel);

        return new Response(ResponseMessage.SUCCESS, userModel);
    }

    @Override
    public Object getUserById(String userId) {
        UserModel user =  userDAO.getUserById(userId);
        if (user == null)
            return new Response(ResponseMessage.NO_RECORD , HttpStatus.NOT_FOUND);
       return new Response(ResponseMessage.SUCCESS , user);
    }

    @Override
    public Object getAllUsers() {
        List userList = userDAO.getAllUsers();
        if (userList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "User List is Empty");
        return  new Response(ResponseMessage.SUCCESS, userList);
    }

    @Override
    public Object editUserById(String userId , UserDto userDto) {
        UserModel userModel = userDAO.getUserById(userId);
        if (userModel == null)
            return new Response(ResponseMessage.NO_RECORD, "User does not exist");
        UserModel editUserModel = new UserModel();
        editUserModel.setUserId(userId);
        editUserModel.setFirstName(userDto.getFirstName());
        editUserModel.setLastName(userDto.getLastName());
        editUserModel.setEmail(userDto.getEmail());
        editUserModel.setCity(userDto.getCity());
        logger.info("Edit user model {}",editUserModel);
        userDAO.editUserById(editUserModel);
        return new Response(ResponseMessage.SUCCESS, editUserModel);
    }

    @Override
    public Object deleteByUserId(String userId) {
        UserModel userModel = userDAO.getUserById(userId);
        if (userModel == null)
            return new Response(ResponseMessage.NO_RECORD, "User does not exist");
        userDAO.deleteByUserId(userId);
        return new Response(ResponseMessage.SUCCESS, HttpStatus.OK);
    }
}
