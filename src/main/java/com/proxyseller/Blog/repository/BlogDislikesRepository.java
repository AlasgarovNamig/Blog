package com.proxyseller.Blog.repository;

import com.proxyseller.Blog.entity.Blog;
import com.proxyseller.Blog.entity.BlogDislikes;
import com.proxyseller.Blog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDislikesRepository extends MongoRepository<BlogDislikes, String> {
    List<BlogDislikes> findByBlog(Blog blog);
    List<BlogDislikes> findByDislikedUser(User dislikedUser);
}
