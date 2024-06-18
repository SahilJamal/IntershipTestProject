package com.test.project.internship.TestApplication.controller;

import com.test.project.internship.TestApplication.dto.UserRequest;
import com.test.project.internship.TestApplication.dto.UserResponse;
import com.test.project.internship.TestApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/fetch")
    public ResponseEntity<UserResponse> userResponse(@RequestParam(value = "userName" , required = false) String userName) {
        if(userName != null) {

            if (!userName.matches("^[A-Za-z0-9_]*$")) {
                throw new IllegalArgumentException("Invalid username");
            }

            UserResponse userResponse = userService.fetchUser(userName);

            if (userResponse.getUserDetails().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponse);
            }

            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        }else{
            if (userService.fetchUser().getUserDetails().isEmpty()) {
                    return ResponseEntity.status(HttpStatus.OK).body(userService.fetchUser());
            }

            return ResponseEntity.status(HttpStatus.OK).body(userService.fetchUser());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> userRequest(@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        if(userResponse.getUserDetails().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
        }
        return  ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
