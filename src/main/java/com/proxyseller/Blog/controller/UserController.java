package com.proxyseller.Blog.controller;

import com.proxyseller.Blog.dto.request.SignUpDto;
import com.proxyseller.Blog.dto.request.UserUpdateRequestDto;
import com.proxyseller.Blog.dto.response.BlogResponseDto;
import com.proxyseller.Blog.entity.User;
import com.proxyseller.Blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.GetAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id,@RequestBody @Validated SignUpDto dto) {
        userService.Update(UserUpdateRequestDto.builder()
                .id(id)
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.Delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
