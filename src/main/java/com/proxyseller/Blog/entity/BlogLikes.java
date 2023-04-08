package com.proxyseller.Blog.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder
@Document(collection = "likes_of_blog")
public class BlogLikes {
    @Id
    private String id;
    @DBRef
    private Blog blog;
    @DBRef
    private User likedUser;
}
