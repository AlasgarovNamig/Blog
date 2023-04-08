package com.proxyseller.Blog.service;

import com.proxyseller.Blog.dto.request.BlogRequestDto;
import com.proxyseller.Blog.dto.response.BlogResponseDto;
import com.proxyseller.Blog.entity.Blog;
import com.proxyseller.Blog.repository.BlogRepository;
import com.proxyseller.Blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;


    @Override
    public List<BlogResponseDto> GetAll() {
        var blogList = blogRepository.findAllByOrderByUpdatedDateDesc();
        List<BlogResponseDto> blogResponseDtoList = IntStream.range(0, blogList.size())
                .mapToObj(blog -> BlogResponseDto.builder()
                        .id(blogList.get(blog).getId())
                        .title(blogList.get(blog).getTitle())
                        .content(blogList.get(blog).getContent())
                        .author(blogList.get(blog).getAuthor())
                        .blogDislikes(blogList.get(blog).getBlogDislikes())
                        .blogLikes(blogList.get(blog).getBlogLikes())
                        .createdDate(blogList.get(blog).getCreatedDate())
                        .updatedDate(blogList.get(blog).getUpdatedDate())
                        .build())
                .collect(Collectors.toList());
        return blogResponseDtoList;
    }

    @Override
    public BlogResponseDto GetById(String id) {
        return null;
    }

    @Override
    public void Create(BlogRequestDto obj) {
        var blog = Blog.builder()
                .title(obj.getTitle())
                .content(obj.getContent())
                .createdDate(new Date())
                .updatedDate(new Date())
                .blogLikes(new HashSet<>())
                .blogDislikes(new HashSet<>())
                .build();
        if (obj.getUser_id() != null) {
            var author = userRepository.findById(obj.getUser_id()).get();
            if (author != null) {
                blog.setAuthor(author);
            }
        }
        blogRepository.save(blog);
    }

    @Override
    public void Update(BlogRequestDto obj) {

    }

    @Override
    public void Delete(String id) {

    }

    /**
     * @author Namig Alasgarov
     * <p>
     * How to work LikeToggle api
     * <p>
     * if blog`s dont have neither like nor dislike, add user blog likes
     * if blog`s have like with current user , removed like
     * if blog`s don`t have like and have dislike, add user blog likes and remove user blog dislike
     */
    @Override
    public void LikeToggle(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var userWhoLikes = userRepository.findByUsername(authentication.getPrincipal().toString()).orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format("User %s was not found in the database", authentication.getPrincipal().toString())));
        var blogToLike = blogRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format("Blog %s was not found in the database", id)));
        if (blogToLike.getBlogLikes().contains(userWhoLikes)) {
            blogToLike.getBlogLikes().remove(userWhoLikes);
        } else {
            if (blogToLike.getBlogDislikes().contains(userWhoLikes)) {
                blogToLike.getBlogDislikes().remove(userWhoLikes);
                blogToLike.getBlogLikes().add(userWhoLikes);
            } else {
                blogToLike.getBlogLikes().add(userWhoLikes);
            }
        }
        blogRepository.save(blogToLike);
    }

    /**
     * @author Namig Alasgarov
     * <p>
     * How to work DislikeToggle api
     * <p>
     * if blog`s dont have neither like nor dislike, add user blog dislikes
     * if blog`s have dislike with current user , removed dislike
     * if blog`s don`t have dislike and have like, add user blog dislikes and remove user blog like
     */
    @Override
    public void DislikeToggle(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var userWhoLikes = userRepository.findByUsername(authentication.getPrincipal().toString()).orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format("User %s was not found in the database", authentication.getPrincipal().toString())));
        var blogToLike = blogRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format("Blog %s was not found in the database", id)));
        if (blogToLike.getBlogDislikes().contains(userWhoLikes)) {
            blogToLike.getBlogDislikes().remove(userWhoLikes);
        } else {
            if (blogToLike.getBlogLikes().contains(userWhoLikes)) {
                blogToLike.getBlogLikes().remove(userWhoLikes);
                blogToLike.getBlogDislikes().add(userWhoLikes);
            } else {
                blogToLike.getBlogDislikes().add(userWhoLikes);
            }
        }
        blogRepository.save(blogToLike);
    }
}
