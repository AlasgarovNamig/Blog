package com.proxyseller.Blog.entity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@Builder
@Document(collection = "blogs")
public class Blog {
    @Id
    private String id;
    private String title;
    private String content;
    private User author;
    private Date createdDate;
    private Date updatedDate;
    @DBRef
    private Set<User> blogLikes = new HashSet<>();
    @DBRef
    private Set<User> blogDislikes = new HashSet<>();
}
