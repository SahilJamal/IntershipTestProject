package com.test.project.internship.TestApplication.service.impl;

import com.test.project.internship.TestApplication.dto.UserInfo;
import com.test.project.internship.TestApplication.dto.UserRequest;
import com.test.project.internship.TestApplication.dto.UserResponse;
import com.test.project.internship.TestApplication.entity.User;
import com.test.project.internship.TestApplication.repository.UserRepository;
import com.test.project.internship.TestApplication.service.UserService;
import com.test.project.internship.TestApplication.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        if(userRepository.existsByEmail(userRequest.getEmail())){
            return UserResponse.builder()
                    .ResponseCode(UserUtils.USER_EXISTS_CODE)
                    .ResponseMessage(UserUtils.USER_EXISTS_MESSAGE)
                    .userDetails(new ArrayList<>())
                    .build();
        }

        User newUser = User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .build();

        userRepository.save(newUser);

        return UserResponse.builder()
                .ResponseCode(UserUtils.USER_CREATION_CODE)
                .ResponseMessage(UserUtils.USER_CREATION_MESSAGE)
                .userDetails(userInfoList(userRequest.getName()))
                .build();
    }

    @Override
    public UserResponse fetchUser(String name) {
        if(userInfoList(name).isEmpty()){
            return UserResponse.builder()
                    .ResponseCode(UserUtils.USER_NOT_EXIST_CODE)
                    .ResponseMessage(UserUtils.USER_NOT_EXIST_MESSAGE)
                    .userDetails(new ArrayList<>())
                    .build();
        }

        return UserResponse.builder()
                .ResponseCode(UserUtils.USER_FOUND_CODE)
                .ResponseMessage(UserUtils.USER_FOUND_MESSAGE)
                .userDetails(userInfoList(name))
                .build();
    }

    @Override
    public UserResponse fetchUser() {
        if(userInfoList().isEmpty()){
            return UserResponse.builder()
                    .ResponseCode(UserUtils.NO_USERS_FOUND_CODE)
                    .ResponseMessage(UserUtils.NO_USERS_FOUND_MESSAGE)
                    .userDetails(new ArrayList<>())
                    .build();
        }

        return UserResponse.builder()
                .ResponseCode(UserUtils.USERS_FOUND_CODE)
                .ResponseMessage(UserUtils.USERS_FOUND_MESSAGE)
                .userDetails(userInfoList())
                .build();
    }

    private List<UserInfo> userInfoList(){
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> UserInfo.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    public List<UserInfo> userInfoList(String name) {
        // Find user by name from repository
        User user = userRepository.findByName(name);

        // Check if user is null
        if (user == null) {
            return List.of(); // or return an empty list, depending on your application logic
        }

        // Map the user to UserInfo using Stream and Collectors.toList()
        return Stream.of(user)
                .map(u -> UserInfo.builder()
                        .email(u.getEmail())
                        .name(u.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
