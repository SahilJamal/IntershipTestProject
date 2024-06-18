package com.test.project.internship.TestApplication.service;

import com.test.project.internship.TestApplication.dto.UserRequest;
import com.test.project.internship.TestApplication.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse fetchUser(String name);
    UserResponse fetchUser();
}
