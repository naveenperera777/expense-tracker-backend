package com.iit.expensetracker.Controller;


import com.iit.expensetracker.Dto.UserDto;
import com.iit.expensetracker.Response.ResponseController;
import com.iit.expensetracker.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController extends ResponseController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserDto userDto){
        logger.info("HIT---> Save user {}",userDto);
        return sendResponse(userService.saveUser(userDto));
    }

//    @GetMapping
//    public Respo



}
