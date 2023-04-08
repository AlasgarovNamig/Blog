package com.proxyseller.Blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String content;

    private String user_id;
}
