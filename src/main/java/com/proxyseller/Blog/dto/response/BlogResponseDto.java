package com.proxyseller.Blog.dto.response;

import com.proxyseller.Blog.entity.User;
import lombok.*;


import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponseDto {
    private String id;
    private String title;
    private String content;
    private User author;
    private Date createdDate;
    private Date updatedDate;
    private Set<User> blogLikes = new HashSet<>();
    private Set<User> blogDislikes = new HashSet<>();

}
