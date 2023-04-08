package com.proxyseller.Blog.dto.response;

import com.proxyseller.Blog.config.validation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data

public class UserResponseDto {
    private  String id;
    private String username;
}
