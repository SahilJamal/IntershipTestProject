package com.test.project.internship.TestApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String ResponseCode;
    private String ResponseMessage;
    private List<UserInfo> userDetails;
}
