package com.iit.expensetracker.Service.Implementation;

import com.iit.expensetracker.DAO.UserDAObject;
import com.iit.expensetracker.Dto.UserDTObject;
import com.iit.expensetracker.Model.User;
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
public class UserServiceImplementation implements UserService {

    private final UserDAObject userDAObject;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    public UserServiceImplementation(UserDAObject userDAObject) {
        this.userDAObject = userDAObject;
    }

    @Override
    public Object saveNewUser(UserDTObject userDTObject) {
        User user = copyDtoToModel(userDTObject);
        logger.info("saving user {}", user.toString());
        userDAObject.saveNewUser(user);
        return new Response(ResponseMessage.SUCCESS, user);
    }

    @Override
    public Object userGetById(String user) {
        User users =  userDAObject.getUserById(user);
        logger.info("get user by user {}", user);
        if (users == null)
            return new Response(ResponseMessage.NO_RECORD , HttpStatus.NOT_FOUND);
       return new Response(ResponseMessage.SUCCESS , users);
    }

    @Override
    public Object getAllUsers() {
        List AllUserList = userDAObject.getAllUsers();
        if (AllUserList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No users found in database");
        return  new Response(ResponseMessage.SUCCESS, AllUserList);
    }

    @Override
    public Object userEditByUserId(String userId , UserDTObject userDTObject) {
        User user = userDAObject.getUserById(userId);
        if (user == null)
            return new Response(ResponseMessage.NO_RECORD, "User Not found in database");
        User userEdit = copyDtoToModel(userDTObject,userId);
        logger.info("Edit user by id {}", userEdit);
        userDAObject.editUserById(userEdit);
        return new Response(ResponseMessage.SUCCESS, userEdit);
    }

    @Override
    public Object userDeleteById(String userId) {
        User user = userDAObject.getUserById(userId);
        if (user == null)
            return new Response(ResponseMessage.NO_RECORD, "User Not found in database");
        logger.info("Edit user by id {}", userId);
        userDAObject.deleteByUserId(userId);
        return new Response(ResponseMessage.SUCCESS, "User Deleted Successfully");
    }

    public User copyDtoToModel(UserDTObject userDTObject){
        User user = new User();
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        user.setUserId(uuid);
        user.setFirstName(userDTObject.getFirstName());
        user.setLastName(userDTObject.getLastName());
        user.setEmail(userDTObject.getEmail());
        user.setTelephoneNumber(userDTObject.getTelephoneNumber());
        return user;
    }

    public User copyDtoToModel(UserDTObject userDTObject, String userId){
        User user = new User();
        user.setUserId(userId);
        user.setFirstName(userDTObject.getFirstName());
        user.setLastName(userDTObject.getLastName());
        user.setEmail(userDTObject.getEmail());
        user.setTelephoneNumber(userDTObject.getTelephoneNumber());
        return  user;
    }
}
