package com.proxyseller.Blog.repository;

import com.proxyseller.Blog.entity.Blog;
import com.proxyseller.Blog.entity.BlogLikes;
import com.proxyseller.Blog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogLikesRepository extends MongoRepository<BlogLikes, String> {
    List<BlogLikes> findByBlog(Blog blog);
    List<BlogLikes> findByLikedUser(User likedUser);
}
