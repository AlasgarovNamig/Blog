package com.proxyseller.Blog.dto.request;

import com.proxyseller.Blog.config.validation.ValidPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class SignUpDto {
    @NotBlank
    private String username;

    @NotBlank
    @ValidPassword
    private String password;

}
