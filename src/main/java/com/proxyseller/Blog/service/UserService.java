package com.proxyseller.Blog.service;

import com.proxyseller.Blog.common.BaseCrud;
import com.proxyseller.Blog.dto.request.SignUpDto;
import com.proxyseller.Blog.dto.request.UserUpdateRequestDto;
import com.proxyseller.Blog.entity.User;


public interface UserService extends BaseCrud<SignUpDto, UserUpdateRequestDto, User> {
}
