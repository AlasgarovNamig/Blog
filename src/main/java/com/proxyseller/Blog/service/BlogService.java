package com.proxyseller.Blog.service;

import com.proxyseller.Blog.common.BaseCrud;
import com.proxyseller.Blog.dto.request.BlogRequestDto;
import com.proxyseller.Blog.dto.response.BlogResponseDto;

public interface BlogService extends BaseCrud<BlogRequestDto,BlogRequestDto, BlogResponseDto> {
    public void LikeToggle(String id);
    public void DislikeToggle(String id);
}
