package com.iit.expensetracker.Service;

import com.iit.expensetracker.Dto.UserDto;

public interface UserService {
    Object saveUser(UserDto userDto);
    Object getUserById(String userId);
    Object getAllUsers();
    Object editUserById(String userId, UserDto userDto);
    Object deleteByUserId(String userId);
}
