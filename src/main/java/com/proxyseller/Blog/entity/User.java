package com.proxyseller.Blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder
@Document(collection = "users")
@EqualsAndHashCode()
public class User {
    @Id
    private String id;
    private String username;
    @JsonIgnore
    private String password;

}
