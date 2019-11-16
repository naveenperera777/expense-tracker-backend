package com.iit.expensetracker.Controller;
import com.iit.expensetracker.Dto.UserDTObject;
import com.iit.expensetracker.Response.ResponseController;
import com.iit.expensetracker.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class Users extends ResponseController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(Users.class);

    public Users(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> userGetById(@PathVariable("id") String user){
        return sendResponse(userService.userGetById(user));
    }

    @PostMapping
    public ResponseEntity<Object> saveNewUser(@RequestBody UserDTObject userDTObject){
        return sendResponse(userService.saveNewUser(userDTObject));
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        return sendResponse(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> userDeleteById(@PathVariable("id") String userId){
        return sendResponse(userService.userDeleteById(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> userEditByUserId(@RequestBody UserDTObject userDTObject, @PathVariable("id") String userId){
        return  sendResponse(userService.userEditByUserId(userId, userDTObject));
    }


}
