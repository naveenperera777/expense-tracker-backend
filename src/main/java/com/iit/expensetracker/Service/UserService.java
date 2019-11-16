package com.iit.expensetracker.Service;

import com.iit.expensetracker.Dto.UserDTObject;

public interface UserService {
    Object saveNewUser(UserDTObject userDTObject);
    Object userGetById(String userId);
    Object getAllUsers();
    Object userEditByUserId(String userId, UserDTObject userDTObject);
    Object userDeleteById(String userId);
}
