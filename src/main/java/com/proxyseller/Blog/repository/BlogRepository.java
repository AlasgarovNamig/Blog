package com.proxyseller.Blog.repository;

import com.proxyseller.Blog.entity.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
    public List<Blog> findAllByOrderByUpdatedDateDesc();
}
