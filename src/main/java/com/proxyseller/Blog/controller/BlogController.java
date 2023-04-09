package com.proxyseller.Blog.controller;

import com.proxyseller.Blog.dto.request.BlogRequestDto;
import com.proxyseller.Blog.dto.response.BlogResponseDto;
import com.proxyseller.Blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {
private  final BlogService blogService;
    @GetMapping()
    public ResponseEntity<List<BlogResponseDto>> getAll() {
        return ResponseEntity.ok(blogService.GetAll());
    }
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Validated BlogRequestDto dto) {
        blogService.Create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{blogId}/like")
    public ResponseEntity<Void> likeBlog(@PathVariable String blogId) {
        blogService.LikeToggle(blogId);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/{blogId}/dislike")
    public ResponseEntity<Void> dislikeBlog(@PathVariable String blogId) {
        blogService.DislikeToggle(blogId);
        return  ResponseEntity.ok().build();
    }
}
