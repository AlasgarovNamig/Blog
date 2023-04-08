package com.proxyseller.Blog.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class UserUpdateRequestDto {

    private String id;
    private String username;

    private String password;
}
