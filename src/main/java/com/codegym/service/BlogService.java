package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.BlogForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Page<Blog> findAll(Pageable pageable);
    Blog findById(Long id);
    void save(BlogForm blogForm);
    void remove(Long id);
    Blog getBlog(BlogForm blogForm);
}
